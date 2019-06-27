package com.aaa.olb.automation.testng;

import org.testng.Assert;
import java.util.ArrayList;
import java.util.List;

public class Assertion {
	public static boolean flag = true;
	public static List<Error> errors = new ArrayList<>();

	public static boolean verifyEquals(Object actual, Object expected) {
		try {
			Assert.assertEquals(actual, expected);
		} catch (Error e) {
			errors.add(e);
			flag = false;
		}
		return flag;
	}

	public static boolean verifyEquals(Object actual, Object expected, String message) {
		try {
			Assert.assertEquals(actual, expected, message);
		} catch (Error e) {
			errors.add(e);
			flag = false;
		}
		return flag;
	}

	public static boolean verifyNulls(Object actual, String message) {
		try {
			Assert.assertNull(actual, message);
		} catch (Error e) {
			errors.add(e);
			flag = false;
		}
		return flag;
	}
	
	public static boolean verifyBooleans(String result, String expect) {
		try {
			Assert.assertTrue(result.equals(expect.equals("FALSE") ? "FALSE" : "TRUE"));
		} catch (Error e) {
			errors.add(e);
			flag = false;
		}
		return flag;
	}
	
	public static boolean verifyFailed(String message) {
		try {
			Assert.fail(message);
		} catch (Error e) {
			errors.add(e);
			flag = false;
		}
		return flag;
	}
}
