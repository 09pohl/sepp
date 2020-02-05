package de.verbund.sepp.main.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author tz
 */

public class Datum {

	public static String toString(LocalDate datum) {
		return datum.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
	}

	public static LocalDate toDate(String datum) {
		return LocalDate.parse(datum, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
	}

	public static boolean between(String datum, String start, String ende) {

		LocalDate vergleichsDatum = toDate(datum);
		LocalDate startDatum = toDate(start);
		LocalDate endDatum = toDate(ende);

		return between(vergleichsDatum, startDatum, endDatum);
	}

	public static boolean between(LocalDate vergleichsDatum, LocalDate startDatum, LocalDate endDatum) {

		return (vergleichsDatum.isAfter(startDatum) && vergleichsDatum.isBefore(endDatum))
				|| vergleichsDatum.isEqual(startDatum) || vergleichsDatum.isEqual(endDatum);
	}

	public static String jetzt() {
		LocalDateTime jetzt = LocalDateTime.now();
		return jetzt.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));
	}
}
