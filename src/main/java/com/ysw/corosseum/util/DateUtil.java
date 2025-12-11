package com.ysw.corosseum.util;

import java.time.*;

public class DateUtil {

    public static Instant now() {
        return LocalDateTime.now(ZoneId.of("Asia/Seoul")).toInstant(ZoneOffset.UTC);
    }

    public static LocalDate today() {
        return LocalDateTime.now(ZoneId.of("Asia/Seoul"))
            .toLocalDate();
    }

    public static long timestamp() {
        return System.currentTimeMillis();
    }

    public static LocalDate nextWeekStartDate() {
        return today().plusWeeks(1).with(java.time.DayOfWeek.MONDAY);
    }

    public static LocalDate nextWeekEndDate() {
        return nextWeekStartDate().with(java.time.DayOfWeek.SUNDAY);
    }

    public static java.util.List<LocalDate> getNextWeekDates() {
        LocalDate start = nextWeekStartDate();
        java.util.List<LocalDate> dates = new java.util.ArrayList<>();
        for (int i = 0; i < 7; i++) {
            dates.add(start.plusDays(i));
        }
        return dates;
    }
}
