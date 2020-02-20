package generator.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import generator.config.GeneratorParameters;
import generator.enums.StringType;
import generator.randomizers.*;
import generator.reflection.ReflectionUtils;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is used to generate random test data for a Java class.
 * <p>
 * johan.den.boer on 28-7-2017.
 */
public final class DataGenerator {
    private final Map<String, Object> objectMap = new HashMap<>();
    private static final Map<String, Randomizer<?>> randomizerMap = new HashMap<>();

    static {
        randomizerMap.put("int", new IntegerRandomizer());
        randomizerMap.put("integer", new IntegerRandomizer());
        randomizerMap.put("string", new StringRandomizer());
        randomizerMap.put("long", new LongRandomizer());
        randomizerMap.put("double", new DoubleRandomizer());
        randomizerMap.put("float", new FloatRandomizer());
        randomizerMap.put("date", new DateRandomizer());
        randomizerMap.put("instant", new InstantRandomizer());
        randomizerMap.put("char", new CharacterRandomizer());
        randomizerMap.put("character", new CharacterRandomizer());
        randomizerMap.put("boolean", new BooleanRandomizer());
        randomizerMap.put("short", new IntegerRandomizer());
        randomizerMap.put("uuid", new UUIDRandomizer());
    }

    private static DataGenerator instance;

    private DataGenerator(final DataGeneratorBuilder builder) {
        this.reset();
        instance = this;
    }

    public static DataGenerator getInstance() {
        return instance;
    }

    public static Map<String, Randomizer<?>> getRandomizerMap() {
        return randomizerMap;
    }

    public Map<String, Object> getObjectMap() {
        return this.objectMap;
    }

    public void reset() {
        this.objectMap.clear();
    }

    /**
     * Get a random list of objects
     *
     * @param cls
     * @param count
     */
    public <T> List<T> randomList(final Class<T> cls, final int count) {
        List<T> dataList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            this.objectMap.clear();
            T data = random(cls);
            dataList.add(data);
        }
        return dataList;
    }

    /**
     * Get random data for class
     *
     * @param cls
     * @return
     */
    public <T> T random(final Class<T> cls) {
        final T object = createRandomData(cls);
        return object;
    }

    private <T> T createRandomData(final Class<T> cls) {
        try {
            if (this.objectMap.containsKey(cls.getSimpleName())) {
                return (T) this.objectMap.get(cls.getSimpleName());
            }
            final T object = cls.newInstance();
            this.objectMap.put(cls.getSimpleName(), object);
            List<Field> fields = ReflectionUtils.getAllFields(cls);
            //Field[] fields = ReflectionUtils.getDeclaredFields(cls);
            for (Field field : fields) {
                if (!GeneratorParameters.excludedFields.contains(field.getName())) {
                    String typeName = field.getType().getSimpleName();
                    String fieldName = field.getName();
                    if (randomizerMap.containsKey(typeName.toLowerCase())) {
                        final Randomizer<?> r = randomizerMap.get(typeName.toLowerCase());
                        Method method = ReflectionUtils.getSetterMethod(cls, fieldName);
                        if (method != null) {
                            method.invoke(object, r.getValue());
                        }
                    } else {
                        Method method = ReflectionUtils.getSetterMethod(cls, fieldName);
                        if (typeName.toLowerCase().equals("set")) {
                            if (ReflectionUtils.isSetter(method)) {
                                final Type setType = method.getGenericParameterTypes()[0];
                                if (setType instanceof ParameterizedType) {
                                    final Type elementType = ((ParameterizedType) setType).getActualTypeArguments()[0];
                                    final SetRandomizer r = new SetRandomizer();
                                    final Class<?> clazz = Class.forName(elementType.getTypeName());
                                    method.invoke(object, r.getValue(clazz));
                                }
                            }
                        } else if (typeName.toLowerCase().equals("list")) {
                            // No setter method for list try the getter method
                            if (method == null) {
                                method = ReflectionUtils.getGetterMethod(cls, fieldName);
                            }
                            if (method != null) {
                                if (ReflectionUtils.isSetter(method)) {
                                    final Type listType = method.getGenericParameterTypes()[0];
                                    if (listType instanceof ParameterizedType) {
                                        final Type elementType = ((ParameterizedType) listType).getActualTypeArguments()[0];
                                        final ListRandomizer r = new ListRandomizer();
                                        final Class<?> clazz = Class.forName(elementType.getTypeName());
                                        method.invoke(object, r.getValue(clazz));
                                    }
                                } else {
                                    Type returnType = method.getGenericReturnType();
                                    if (returnType instanceof ParameterizedType) {
                                        final Type elementType = ((ParameterizedType) returnType).getActualTypeArguments()[0];
                                        final ListRandomizer r = new ListRandomizer();
                                        Class<?> clazz = Class.forName(elementType.getTypeName());
                                        int modifiers = clazz.getModifiers();
                                        boolean b = Modifier.isAbstract(modifiers);
                                        if (b) {
                                            String packageName = clazz.getPackage().getName();
                                            Class[] classes = ReflectionUtils.getClassesInPackage(packageName);
                                            for (Class c : classes) {
                                                if (clazz.getSimpleName().equals(c.getSuperclass().getSimpleName())) {
                                                    List<?> l = r.getValue(c);
                                                    List<Object> l1 = (List<Object>) method.invoke(object, null);
                                                    l1.addAll(l);
                                                }
                                            }
                                        } else {
                                            List<?> l = r.getValue(clazz);
                                            List<Object> l1 = (List<Object>) method.invoke(object, null);
                                            l1.addAll(l);
                                        }
                                    }
                                }
                            }
                        } else {
                            if (method != null) {
                                final Class<?>[] parameterTypes = method.getParameterTypes();
                                if (parameterTypes.length > 1) {
                                    throw new IllegalArgumentException("Number of parameters in setter does not match : " + method.getName());
                                }
                                if (parameterTypes[0].isEnum()) {
                                    final EnumRandomizer r = new EnumRandomizer();
                                    method.invoke(object, r.getValue(parameterTypes[0]));
                                } else {
                                    final T child = (T) createRandomData(parameterTypes[0]);
                                    method.invoke(object, child);
                                }
                            }
                        }
                    }
                }
            }
            return object;
        } catch (final Exception e) {
            throw new IllegalArgumentException("Failed to generator data for class " + cls.getSimpleName());
        }
    }

    /**
     * Get random data as Json
     *
     * @param cls
     * @return
     */
    public <T> String randomAsJson(final Class<T> cls) {
        final T object = createRandomData(cls);
        final Gson gson = new GsonBuilder().create();
        return gson.toJson(object);
    }

    /**
     * Builder for DataGenerator
     *
     * @author johan.den.boer
     */
    public static class DataGeneratorBuilder {

        public DataGeneratorBuilder() {
            GeneratorParameters.excludedFields.clear();
        }

        public DataGeneratorBuilder withCollectionSize(final int size) {
            GeneratorParameters.MAX_COLLECTION__SIZE = size;
            return this;
        }

        public DataGeneratorBuilder withIntegerRange(final int low, final int high) {
            GeneratorParameters.MIN_RANGE_INTEGER = low;
            GeneratorParameters.MAX_RANGE_INTEGER = high;
            return this;
        }

        public DataGeneratorBuilder withLongRange(final long low, final long high) {
            GeneratorParameters.MIN_RANGE_LONG = low;
            GeneratorParameters.MAX_RANGE_LONG = high;
            return this;
        }

        public DataGeneratorBuilder withFloatRange(final float low, final float high) {
            GeneratorParameters.MIN_RANGE_FLOAT = low;
            GeneratorParameters.MAX_RANGE_FLOAT = high;
            return this;
        }

        public DataGeneratorBuilder withDoubleRange(final double low, final double high) {
            GeneratorParameters.MIN_RANGE_DOUBLE = low;
            GeneratorParameters.MAX_RANGE_DOUBLE = high;
            return this;
        }

        public DataGeneratorBuilder withStringLength(final int length) {
            GeneratorParameters.STRING_LENGTH = length;
            return this;
        }

        public DataGeneratorBuilder withDateRange(final int low, final int high) {
            GeneratorParameters.MIN_RANGE_DATE = low;
            GeneratorParameters.MAX_RANGE_DATE = high;
            return this;
        }

        public DataGeneratorBuilder withStringCase(final StringType stringCase) {
            GeneratorParameters.STRING_CASE = stringCase;
            return this;
        }

        public DataGeneratorBuilder withExcludeField(final String... fieldNames) {
            for (final String fieldName : fieldNames) {
                GeneratorParameters.excludedFields.add(fieldName);
            }
            return this;
        }

        public DataGenerator build() {
            return new DataGenerator(this);
        }
    }

}
