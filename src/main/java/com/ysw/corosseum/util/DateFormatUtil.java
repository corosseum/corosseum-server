package com.ysw.corosseum.util;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateFormatUtil {

	private static final DateTimeFormatter FORMATTER =
		DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.of("Asia/Seoul"));

	public static String format(Instant instant) {
		if (instant == null) {
			return null;
		}
		return FORMATTER.format(instant);
	}
}
