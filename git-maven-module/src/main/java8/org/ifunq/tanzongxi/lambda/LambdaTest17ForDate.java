package org.ifunq.tanzongxi.lambda;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class LambdaTest17ForDate {

    public static void main(String[] args) {
        // 获取当前的日期时间
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
        System.out.println(currentTime.format(formatter1));
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH时mm分ss秒");
        System.out.println(currentTime.format(formatter2));

        // Europe/Paris时区日期时间
        ZoneId id = ZoneId.of("Europe/Paris");
        ZonedDateTime zonedDateTime = ZonedDateTime.now(id);
        System.out.println(zonedDateTime.format(formatter1));
        System.out.println(zonedDateTime.format(formatter2));

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        System.out.println(dateFormat.format(new Date()));
    }
}
