package de.verbund.sepp.main.utils;

public class StringHelfer {

	public static boolean isNullEmptyOrWhitespace(String text) {
		return text == null || text.trim().isEmpty();
	}

	public static boolean isNullOrEmpty(String text) {
		return text == null || text.isEmpty();
	}

	public static String ersterBuchstabeGross(String text) {
		if (StringHelfer.isNullEmptyOrWhitespace(text)) {
			return text;
		}
		return text.substring(0, 1).toUpperCase() + text.substring(1);
	}
}
