package com.hotel.holiday.dream.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Slf4j
public class TimeLog {

    private final long startTime;
    private long endTime;

    public TimeLog() {
        this("");
    }

    public TimeLog(String info) {
        if (!StringUtils.isEmpty(info)) {
            log.info(info);
        }
        this.startTime = new Date().getTime();
    }

    public void end() {
        this.end("");
    }

    public void end(String info) {
        if (!StringUtils.isEmpty(info)) {
            log.trace(info);
        }
        this.endTime = new Date().getTime();
        long millis = endTime - startTime;
        String total = String.format("%d min, %d sec",
                TimeUnit.MILLISECONDS.toMinutes(millis),
                TimeUnit.MILLISECONDS.toSeconds(millis) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))
        );
        log.info(String.format("%s : %S", info, total));
    }
}
