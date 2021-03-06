package com.aaa.olb.automation.utils;

public class ParameterExacter {

	public static String[] getParamters(String text, int length) {
		String[] parameters = text.split(",");
		for (int i = 0; i < length; i++) {
			parameters[i] = parameters[i] != null ? parameters[i] : "";
		}
		return parameters;

	}

	public static int[] getIntParameters(String text, int length) {
		String[] parameters = getParamters(text, length);
		int[] intParameters = new int[length];
		for (int i = 0; i < length; i++) {
			intParameters[i] = parameters[i] != null ? Integer.parseInt(parameters[i]) : 0;
		}
		return intParameters;
	}

	public static double[] getDoubleParameters(String text, int length) {
		String[] parameters = getParamters(text, length);
		double[] intParameters = new double[length];
		for (int i = 0; i < length; i++) {
			intParameters[i] = parameters[i] != null ? Double.parseDouble(parameters[i]) : 0;
		}
		return intParameters;
	}
}
