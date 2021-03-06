package generator.randomizers;

import generator.config.GeneratorParameters;

import java.util.Random;

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
