package de.verbund.sepp.main.utils;

public class DateiInfoHelfer {

	public static String[][] getZeilenArray(String text) {
		String[] zeilen = text.split("\\r?\\n");
		String[][] result = new String[zeilen.length][2];
		for (int i = 0; i < zeilen.length; i++) {
			result[i] = zeilen[i].split(":", 2);
		}
		return result;
	}
}
