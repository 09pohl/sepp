package de.verbund.sepp.main.utils;

import java.nio.file.attribute.FileTime;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DatumHelfer extends Datum {

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
