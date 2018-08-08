package nl.ict.psa.utils.generator.randomizers;

import java.util.Random;

import nl.ict.psa.utils.generator.config.GeneratorParameters;
import nl.ict.psa.utils.generator.enums.StringType;

public class StringRandomizer implements Randomizer<String> {

	public StringRandomizer() {
	}

	@Override
	public String getValue() {
		StringBuilder sb = new StringBuilder(GeneratorParameters.STRING_LENGTH);
		for (int i = 0; i < GeneratorParameters.STRING_LENGTH; i++) {
			sb.append(GeneratorParameters.STRING_SEQUENCE
					.charAt(new Random().nextInt(GeneratorParameters.STRING_SEQUENCE.length())));
		}
		if (GeneratorParameters.STRING_CASE == StringType.LOWER) {
			return sb.toString().toLowerCase();
		}
		if (GeneratorParameters.STRING_CASE == StringType.UPPER) {
			return sb.toString().toUpperCase();
		}
		return sb.toString();
	}
}
