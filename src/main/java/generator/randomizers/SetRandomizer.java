package generator.randomizers;

import generator.api.DataGenerator;
import generator.config.GeneratorParameters;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class SetRandomizer {

    public SetRandomizer() {
    }

    public <T> Set<T> getValue(final Class<T> cls) {
        final Random random = new Random();
        final int collectionSize = random.nextInt(GeneratorParameters.MAX_COLLECTION__SIZE);
        final Set<T> values = new HashSet<>();
        for (int i = 0; i < collectionSize; i++) {
            try {
                final String type = cls.getSimpleName().toLowerCase();
                if (DataGenerator.getRandomizerMap().containsKey(type)) {
                    final Randomizer<?> r = DataGenerator.getRandomizerMap().get(type);
                    values.add((T) r.getValue());
                } else {
                    final DataGenerator r = DataGenerator.getInstance();
                    final T object = r.random(cls);
                    values.add(object);
                    DataGenerator.getInstance().getObjectMap().remove(cls.getSimpleName());
                }
            } catch (final Exception e) {
                throw new IllegalArgumentException("Invalid argument for SetRandomizer " + cls.getSimpleName());
            }
        }
        return values;
    }

}
