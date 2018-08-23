package generator.randomizers;

import java.util.Random;

import generator.config.GeneratorParameters;

public class CharacterRandomizer implements Randomizer<Character> {

	public CharacterRandomizer() {
	}

	@Override
	public Character getValue() {
		Random random = new Random();
		int pos = random.nextInt(GeneratorParameters.STRING_SEQUENCE.length());
		return GeneratorParameters.STRING_SEQUENCE.charAt(pos);
	}
}
