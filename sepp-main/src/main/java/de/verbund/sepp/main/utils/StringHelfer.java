package de.verbund.sepp.main.utils;

public class StringHelfer {

	public static boolean istNullLeerOderWhitespace(String text) {
		return text == null || text.trim().isEmpty();
	}

	public static boolean istNullOderLeer(String text) {
		return text == null || text.isEmpty();
	}

	public static String ersterBuchstabeGross(String text) {
		if (StringHelfer.istNullLeerOderWhitespace(text)) {
			return text;
		}
		return text.substring(0, 1).toUpperCase() + text.substring(1);
	}
}
