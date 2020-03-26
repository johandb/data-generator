package generator.randomizers;

import generator.config.GeneratorParameters;

import java.math.BigInteger;
import java.util.Random;

public class BigIntegerRandomizer implements Randomizer<BigInteger> {

    @Override
    public BigInteger getValue() {
        Random random = new Random();
        int value = GeneratorParameters.MIN_RANGE_INTEGER + random.nextInt();
        return BigInteger.valueOf(value);
    }
}
