package generator.randomizers;

import generator.config.GeneratorParameters;

import java.util.Random;

public class IntegerRandomizer implements Randomizer<Integer> {

	public IntegerRandomizer() {
	}

	@Override
	public Integer getValue() {
		Random random = new Random();
		int spread = Math.abs(GeneratorParameters.MAX_RANGE_INTEGER - GeneratorParameters.MIN_RANGE_INTEGER);
		int value = GeneratorParameters.MIN_RANGE_INTEGER + random.nextInt(spread);
		return value;
	}

}
