package nl.ict.psa.utils.generator.randomizers;

import java.util.Date;
import java.util.Random;

import nl.ict.psa.utils.generator.config.GeneratorParameters;

public class DateRandomizer implements Randomizer<Date> {

	public DateRandomizer() {
	}

	@Override
	public Date getValue() {
		Date today = new Date();
		long l = today.getTime();
		int spread = Math.abs(GeneratorParameters.MAX_RANGE_DATE - GeneratorParameters.MIN_RANGE_DATE);
		if (spread > 0) {
			l += (GeneratorParameters.MIN_RANGE_DATE * 24 * 60 * 60 * 1000);
			l = l + (new Random().nextInt(spread * 24 * 60 * 60 * 1000));
		}
		today.setTime(l);
		return today;
	}

}
