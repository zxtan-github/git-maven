package org.ifunq.tanzongxi.lambda;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class LambdaTest16ForDate {

    public static void main(String[] args) {

        // 获取当前时间日期
        ZonedDateTime date1 = ZonedDateTime.parse("2015-12-03T10:15:30+05:30[Asia/Shanghai]");
        System.out.println("Asia/Shanghai date1: " + date1);

        ZoneId currentZone = ZoneId.systemDefault();
        System.out.println("当期时区: " + currentZone);

        ZoneId id = ZoneId.of("Europe/Paris");
        System.out.println("ZoneId: " + id);

        ZonedDateTime date2 = ZonedDateTime.of(LocalDateTime.now(), id);
        System.out.println("Europe/Paris date2: " + date2);
    }
}
