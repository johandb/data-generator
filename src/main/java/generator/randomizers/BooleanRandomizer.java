package generator.randomizers;

public class BooleanRandomizer implements Randomizer<Boolean> {

	public BooleanRandomizer() {
	}

	@Override
	public Boolean getValue() {
		IntegerRandomizer random = new IntegerRandomizer();
		int value = random.getValue();
		return value % 2 == 0 ? true : false;
	}
}
