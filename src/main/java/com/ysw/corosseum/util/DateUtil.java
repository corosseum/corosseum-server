package com.ysw.corosseum.util;

import java.time.*;
import java.util.ArrayList;
import java.util.List;

public class DateUtil {

    public static Instant now() {
        return Instant.now();
    }

    public static LocalDate today() {
        return LocalDateTime.now(ZoneId.of("Asia/Seoul"))
            .toLocalDate();
    }

    public static long timestamp() {
        return System.currentTimeMillis();
    }

    public static LocalDate nextWeekStartDate() {
        return today().plusWeeks(1).with(DayOfWeek.MONDAY);
    }

    public static LocalDate nextWeekEndDate() {
        return nextWeekStartDate().with(DayOfWeek.SUNDAY);
    }

    public static java.util.List<LocalDate> getNextWeekDates() {
        LocalDate start = nextWeekStartDate();
        List<LocalDate> dates = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            dates.add(start.plusDays(i));
        }
        return dates;
    }
}
