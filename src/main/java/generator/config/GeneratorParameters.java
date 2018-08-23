package generator.config;

import generator.enums.StringType;

import java.util.HashSet;
import java.util.Set;

public class GeneratorParameters {

	private GeneratorParameters() {
	}

	public static Set<String> excludedFields = new HashSet<>();

	// StringRandomizer
	public static String STRING_SEQUENCE = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	public static StringType STRING_CASE = StringType.BOTH;

	public static int STRING_LENGTH = 8;

	// IntegerRandomizer
	public static int MIN_RANGE_INTEGER = 0;
	public static int MAX_RANGE_INTEGER = 10;

	// LongRandomizer
	public static long MIN_RANGE_LONG = 0;
	public static long MAX_RANGE_LONG = 10;

	// FloatRandomizer
	public static float MIN_RANGE_FLOAT = 0.00f;
	public static float MAX_RANGE_FLOAT = 20.00f;

	// DoubleRandomizer
	public static double MIN_RANGE_DOUBLE = 0.00;
	public static double MAX_RANGE_DOUBLE = 20.00;

	// DateRandomizer
	public static int MIN_RANGE_DATE = 0;
	public static int MAX_RANGE_DATE = 0;

	// ListRandomize
	public static int MAX_COLLECTION__SIZE = 10;
}
