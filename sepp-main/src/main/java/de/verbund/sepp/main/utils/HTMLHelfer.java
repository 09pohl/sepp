package de.verbund.sepp.main.utils;

public class HTMLHelfer {
	public static final String OPEN = "<html>";
	public static final String CLOSE = "</html>";
	public static final String HEADER_O = "<header>";
	public static final String HEADER_C = "</header>";
	public static final String H1_O = "<h1>";
	public static final String H1_C = "</h1>";
	public static final String H2_O = "<h2>";
	public static final String H2_C = "</h2>";
	public static final String H3_O = "<h3>";
	public static final String H3_C = "</h3>";
	public static final String H4_O = "<h4>";
	public static final String H4_C = "</h4>";
	public static final String H5_O = "<h5>";
	public static final String H5_C = "</h5>";
	public static final String H6_O = "<h6>";
	public static final String H6_C = "</h6>";
	public static final String BR = "<br>";
	public static final String FONT_C = "</font>";
	public static final String CENTER_O = "<center>";
	public static final String CENTER_C = "</center>";

	public static String setColour(String input, String colour) {
		return OPEN + fontColourOpen(colour) + input + FONT_C + CLOSE;
	}

	public static String fontColourOpen(String colour) {
		return "<font color=\"" + colour + "\">";
	}

	public static String setCenter(String input) {
		return OPEN + CENTER_O + input + CENTER_C + CLOSE;
	}
}
