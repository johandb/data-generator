package generator.randomizers;

import generator.config.GeneratorParameters;

import java.util.Random;

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
