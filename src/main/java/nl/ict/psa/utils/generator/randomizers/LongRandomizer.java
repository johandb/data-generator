package nl.ict.psa.utils.generator.randomizers;

import java.util.Random;

import nl.ict.psa.utils.generator.config.GeneratorParameters;

public class LongRandomizer implements Randomizer<Long> {

	public LongRandomizer() {
	}

	@Override
	public Long getValue() {
		Random random = new Random();
		int spread = Math.abs((int) GeneratorParameters.MAX_RANGE_LONG - (int) GeneratorParameters.MIN_RANGE_LONG);
		long value = GeneratorParameters.MIN_RANGE_LONG + random.nextInt(spread);
		return value;
	}

}
