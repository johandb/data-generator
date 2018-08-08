package nl.ict.psa.utils.generator.reflection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.Test;

import nl.ict.psa.utils.generator.model.Person;

/**
 * Created by johan.den.boer on 28-7-2017.
 */
public class ReflectionUtilsTest {

	@Test
	public void testAllMethods() {
		Method[] methods = ReflectionUtils.getMethods(Person.class);
		assertTrue(methods.length > 0);
	}

	@Test
	public void testAllPublicFields() {
		Field[] fields = ReflectionUtils.getPublicFields(Person.class);
		assertEquals(fields.length, 0);
	}

	@Test
	public void testAllDeclaredFields() {
		Field[] fields = ReflectionUtils.getDeclaredFields(Person.class);
		assertTrue(fields.length > 0);
	}

}
