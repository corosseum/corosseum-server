package com.ysw.corosseum.util;

import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class TimeUtil {

    public static Instant now() {
        return Instant.now();
    }

}
