package com.aaa.olb.automation.utils;

import java.util.StringTokenizer;

import com.mifmif.common.regex.Generex;

public class GenerateValueByRegex {

	@SuppressWarnings("static-access")
	public static String getRandom(String value) throws Exception {
		if (value.indexOf("?=") >= 0 || value.indexOf("?<=") >= 0 || value.indexOf("?!") >= 0
				|| value.indexOf("<!=") >= 0) {
			throw new Exception("Not Supported Regex");
		}
		String regex = value.substring(value.indexOf("[regex](") + 8, value.lastIndexOf(")")).replace("^", "")
				.replace("$", "").replace("\\\\", "\\").replace("?:", "");

		/*
		 * specialCharsRegex skip the unsupported characters
		 */
		String specialCharsRegex = "@| |#";
		String specialChars = join(specialCharsRegex.split("\\|"), "");
		StringBuilder sb = new StringBuilder();
		StringTokenizer strToken = new StringTokenizer(regex, specialChars, true);
		while (strToken.hasMoreTokens()) {
			String subStr = strToken.nextToken();
			if (!subStr.matches(specialCharsRegex)) {
				Generex generex = new Generex(subStr);
				if (generex.isValidPattern(subStr)) {
					subStr = generex.random();
				}
			}
			sb.append(subStr);
		}

		return sb.toString();
	}

	public static String join(Object[] o, String flag) {
		StringBuffer str_buff = new StringBuffer();

		for (int i = 0, len = o.length; i < len; i++) {
			str_buff.append(String.valueOf(o[i]));
			if (i < len - 1)
				str_buff.append(flag);
		}

		return str_buff.toString();
	}
}
