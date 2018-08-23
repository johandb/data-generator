package nl.ict.psa.utils.generator.randomizers;

import java.util.Random;

import nl.ict.psa.utils.generator.config.GeneratorParameters;

public class DoubleRandomizer implements Randomizer<Double> {

	public DoubleRandomizer() {
	}

	@Override
	public Double getValue() {
		Random random = new Random();
		int spread = Math.abs((int) GeneratorParameters.MAX_RANGE_DOUBLE - (int) GeneratorParameters.MIN_RANGE_DOUBLE);
		double value = GeneratorParameters.MIN_RANGE_DOUBLE + random.nextInt(spread);
		return value;
	}
}
