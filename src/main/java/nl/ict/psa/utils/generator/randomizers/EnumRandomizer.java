package nl.ict.psa.utils.generator.randomizers;

import java.util.Random;

public class EnumRandomizer {

	public EnumRandomizer() {
	}

	public <T> T getValue(final Class<T> clazz) {
		Random random = new Random();
		int x = random.nextInt(clazz.getEnumConstants().length);
		return clazz.getEnumConstants()[x];
	}
}
