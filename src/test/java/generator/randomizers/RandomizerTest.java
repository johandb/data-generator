package generator.randomizers;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import generator.config.GeneratorParameters;
import nl.ict.psa.utils.generator.model.Status;

public class RandomizerTest {

	private static Map<String, Randomizer<?>> randomizerMap = new HashMap<>();

	static {
		randomizerMap.put("int", new IntegerRandomizer());
		randomizerMap.put("Integer", new IntegerRandomizer());
	}

	@Test
	public void testLookupIntegerRandomizer() {
		Randomizer<?> r = randomizerMap.get("Integer");
		assertTrue((Integer) r.getValue() >= GeneratorParameters.MIN_RANGE_INTEGER
				&& (Integer) r.getValue() < GeneratorParameters.MAX_RANGE_INTEGER);
	}

	@Test
	public void testLookupRandomizerNotInMap() {
		Randomizer<?> r = randomizerMap.get("unknown");
		assertNull(r);
	}

	@Test
	public void testIntegerRandomizerInRange() {
		GeneratorParameters.MIN_RANGE_INTEGER = 10;
		GeneratorParameters.MAX_RANGE_INTEGER = 15;
		IntegerRandomizer r = new IntegerRandomizer();
		int value = r.getValue();
		assertTrue(value >= GeneratorParameters.MIN_RANGE_INTEGER && value < GeneratorParameters.MAX_RANGE_INTEGER);
	}

	@Test
	public void testIntegerRandomizerInDefaultRange() {
		IntegerRandomizer r = new IntegerRandomizer();
		int value = r.getValue();
		assertTrue(value >= GeneratorParameters.MIN_RANGE_INTEGER && value < GeneratorParameters.MAX_RANGE_INTEGER);
	}

	@Test
	public void testLongRandomizerInRange() {
		LongRandomizer r = new LongRandomizer();
		long value = r.getValue();
		assertTrue(value >= GeneratorParameters.MIN_RANGE_LONG && value < GeneratorParameters.MAX_RANGE_LONG);
	}

	@Test
	public void testStringRandomizer() {
		StringRandomizer r = new StringRandomizer();
		assertNotNull(r.getValue());
	}

	@Test
	public void testDateRandomizer() {
		DateRandomizer r = new DateRandomizer();
		assertNotNull(r.getValue());
	}

	@Test
	public void testDateInRangeRandomizer() {
		DateRandomizer r = new DateRandomizer();
		GeneratorParameters.MIN_RANGE_DATE = -1;
		GeneratorParameters.MAX_RANGE_DATE = 1;
		assertNotNull(r.getValue());
	}

	@Test
	public void testEnumRandomizer() {
		EnumRandomizer random = new EnumRandomizer();
		Status status = random.getValue(Status.class);
		assertNotNull(status);
	}

	@Test
	public void testCharacterRandomizer() {
		CharacterRandomizer random = new CharacterRandomizer();
		char c = random.getValue();
		assertTrue(c >= 'A' && c <= 'z');
	}
}
