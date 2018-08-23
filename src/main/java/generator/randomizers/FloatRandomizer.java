package generator.randomizers;

import java.util.Random;

import generator.config.GeneratorParameters;

public class FloatRandomizer implements Randomizer<Float> {

	public FloatRandomizer() {
	}

	@Override
	public Float getValue() {
		Random random = new Random();
		int spread = Math.abs((int) GeneratorParameters.MAX_RANGE_FLOAT - (int) GeneratorParameters.MIN_RANGE_FLOAT);
		float value = GeneratorParameters.MIN_RANGE_FLOAT + random.nextInt(spread);
		return value;
	}

}
