package generator.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by johan.den.boer on 28-7-2017.
 */
public class ReflectionUtils {

	public static Method[] getMethods(final Class<?> cls) {
		return cls.getMethods();
	}

	public static Method[] getDeclaredMethods(final Class<?> cls) {
		return cls.getDeclaredMethods();
	}

	public static Field[] getPublicFields(final Class<?> cls) {
		return cls.getFields();
	}

	public static Field[] getDeclaredFields(final Class<?> cls) {
		return cls.getDeclaredFields();
	}

	public static boolean isPrivateField(final Field field) {
		if (Modifier.isPrivate(field.getModifiers())) {
			return true;
		}
		return false;
	}

	public static List<Method> getSetterMethods(final Class<?> cls) {
		List<Method> setterMethods = new ArrayList<>();
		for (Method method : getMethods(cls)) {
			if (isSetter(method)) {
				setterMethods.add(method);
			}
		}
		return setterMethods;
	}

	public static Method getSetterMethod(final Class<?> cls, final String fieldName) {
		String setterName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
		return getMethodByname(cls, setterName);
	}

	public static Method getGetterMethod(final Class<?> cls, final String fieldName) {
		String setterName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
		return getMethodByname(cls, setterName);
	}

	public static List<Method> getGetterMethods(final Class<?> cls) {
		List<Method> getterMethods = new ArrayList<>();
		for (Method method : getMethods(cls)) {
			if (isGetter(method)) {
				getterMethods.add(method);
			}
		}
		return getterMethods;
	}

	public static boolean isSetter(final Method method) {
		if (!method.getName().startsWith("set")) {
			return false;
		}
		if (method.getParameterTypes().length != 1) {
			return false;
		}
		return true;
	}

	public static Method getMethodByname(final Class<?> cls, final String methodName) {
		Method[] methods = ReflectionUtils.getDeclaredMethods(cls);
		for(Method m : methods) {
			if(m.getName().equals(methodName)) {
				return m;
			}
		}
		return null;
	}

	public static boolean isGetter(final Method method) {
		if (!method.getName().startsWith("get")) {
			return false;
		}
		if (method.getParameterTypes().length != 0) {
			return false;
		}
		return true;
	}

	private static boolean isInstantiable(final Class<?> cls) {
		return !cls.isInterface() && !Modifier.isAbstract(cls.getModifiers());
	}
}
