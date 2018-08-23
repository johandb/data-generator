package generator.randomizers;

import generator.api.DataGenerator;
import generator.config.GeneratorParameters;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListRandomizer
{

    public ListRandomizer()
    {
    }

    public <T> List<T> getValue(final Class<T> cls)
    {
        final Random random = new Random();
        final int collectionSize = random.nextInt(GeneratorParameters.MAX_COLLECTION__SIZE);
        final List<T> values = new ArrayList<>();
        for (int i = 0; i < collectionSize; i++)
        {
            try
            {
                final String type = cls.getSimpleName().toLowerCase();
                if (DataGenerator.getRandomizerMap().containsKey(type))
                {
                    final Randomizer<?> r = DataGenerator.getRandomizerMap().get(type);
                    values.add((T) r.getValue());
                }
                else
                {
                    final DataGenerator r = DataGenerator.getInstance();
                    final T object = r.random(cls);
                    values.add(object);
                    DataGenerator.getInstance().getObjectMap().remove(cls.getSimpleName());
                }
            }
            catch (final Exception e)
            {
                throw new IllegalArgumentException("Invalid argument for Listrandomizer " + cls.getSimpleName());
            }
        }
        return values;
    }
}
