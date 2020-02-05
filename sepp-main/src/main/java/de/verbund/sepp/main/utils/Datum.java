package de.verbund.sepp.main.utils;

import java.nio.file.attribute.FileTime;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
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

	private static final DateTimeFormatter DATE_FORMATTER_WITH_TIME = DateTimeFormatter
			.ofPattern("MMM d, yyyy HH:mm:ss");

	public static String fileTimeToString(FileTime fileTime) {
		String s = parseToString(fileTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
		return s;
	}

	public static FileTime fileTimeFromString(String dateTimeString) {
		LocalDateTime localDateTime = parseFromString(dateTimeString);
		return FileTime.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	public static String parseToString(LocalDateTime localDateTime) {
		return localDateTime.format(DATE_FORMATTER_WITH_TIME);
	}

	public static LocalDateTime parseFromString(String date) {
		return LocalDateTime.parse(date, DATE_FORMATTER_WITH_TIME);
	}
}
