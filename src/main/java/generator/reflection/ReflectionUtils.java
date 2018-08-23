package generator.reflection;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by johan.den.boer on 28-7-2017.
 */
public class ReflectionUtils {

	public static Method[] getMethods(final Class<?> cls) {
		return cls.getMethods();
	}

	public static Method[] getDeclaredMethods(final Class<?> cls) {
		return cls.getDeclaredMethods();
	}

	public static List<Method> getAllMethods(final Class<?> cls) {
		List<Method> methods = new ArrayList<>();
		for (Class<?> c = cls; c != null; c = c.getSuperclass()) {
			methods.addAll(Arrays.asList(c.getDeclaredMethods()));
		}
		return methods;
	}

	public static Field[] getPublicFields(final Class<?> cls) {
		return cls.getFields();
	}

	public static Field[] getDeclaredFields(final Class<?> cls) {
		return cls.getDeclaredFields();
	}

	public static List<Field> getAllFields(Class<?> type) {
		List<Field> fields = new ArrayList<>();
		for (Class<?> c = type; c != null; c = c.getSuperclass()) {
			fields.addAll(Arrays.asList(c.getDeclaredFields()));
		}
		return fields;
	}

	public static boolean isPrivateField(final Field field) {
		if (Modifier.isPrivate(field.getModifiers())) {
			return true;
		}
		return false;
	}

	public static List<Method> getSetterMethods(final Class<?> cls) {
		List<Method> setterMethods = new ArrayList<>();
		for (Method method : getMethods(cls)) {
			if (isSetter(method)) {
				setterMethods.add(method);
			}
		}
		return setterMethods;
	}

	public static Method getSetterMethod(final Class<?> cls, final String fieldName) {
		String setterName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
		return getMethodByname(cls, setterName);
	}

	public static Method getGetterMethod(final Class<?> cls, final String fieldName) {
		String setterName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
		return getMethodByname(cls, setterName);
	}

	public static List<Method> getGetterMethods(final Class<?> cls) {
		List<Method> getterMethods = new ArrayList<>();
		for (Method method : getMethods(cls)) {
			if (isGetter(method)) {
				getterMethods.add(method);
			}
		}
		return getterMethods;
	}

	public static boolean isSetter(final Method method) {
		if (!method.getName().startsWith("set")) {
			return false;
		}
		if (method.getParameterTypes().length != 1) {
			return false;
		}
		return true;
	}

	public static Method getMethodByname(final Class<?> cls, final String methodName) {
		List<Method> methods = getAllMethods(cls);
		for(Method m : methods) {
			if(m.getName().equals(methodName)) {
				return m;
			}
		}
		return null;
	}

	public static boolean isGetter(final Method method) {
		if (!method.getName().startsWith("get")) {
			return false;
		}
		if (method.getParameterTypes().length != 0) {
			return false;
		}
		return true;
	}

	private static boolean isInstantiable(final Class<?> cls) {
		return !cls.isInterface() && !Modifier.isAbstract(cls.getModifiers());
	}

	public static Class[] getClassesInPackage(String pckgname) {
		File directory = getPackageDirectory(pckgname);
		if (!directory.exists()) {
			throw new IllegalArgumentException("Could not get directory resource for package " + pckgname + ".");
		}

		return getClassesInPackage(pckgname, directory);
	}

	private static Class[] getClassesInPackage(String pckgname, File directory) {
		List<Class> classes = new ArrayList<Class>();
		for (String filename : directory.list()) {
			if (filename.endsWith(".class")) {
				String classname = buildClassname(pckgname, filename);
				try {
					classes.add(Class.forName(classname));
				} catch (ClassNotFoundException e) {
					System.err.println("Error creating class " + classname);
				}
			}
		}
		return classes.toArray(new Class[classes.size()]);
	}

	private static String buildClassname(String pckgname, String filename) {
		return pckgname + '.' + filename.replace(".class", "");
	}

	private static File getPackageDirectory(String pckgname) {
		ClassLoader cld = Thread.currentThread().getContextClassLoader();
		if (cld == null) {
			throw new IllegalStateException("Can't get class loader.");
		}

		URL resource = cld.getResource(pckgname.replace('.', '/'));
		if (resource == null) {
			throw new RuntimeException("Package " + pckgname + " not found on classpath.");
		}

		return new File(resource.getFile());
	}

}
