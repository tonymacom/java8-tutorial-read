package com.tony.unit.time;

import javafx.util.converter.LocalDateTimeStringConverter;
import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

/**
 * @author www.yamibuy.com
 * @desc :
 * @date 2019/3/22
 * <b>版权所有：</b>版权所有(C) 2018，www.yamibuy.com<br>
 */
public class LocalDate1 {


    @Test
    public void test() {
        LocalDate today = LocalDate.now();
        System.out.println(today.toString()); //2019-03-22

        LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
        System.out.println(tomorrow); //2019-03-23

        LocalDate yesterday = tomorrow.minusDays(2);
        System.out.println(yesterday); //2019-03-21

        LocalDate independenceDay = LocalDate.of(2014, Month.JULY, 4);
        System.out.println(independenceDay); //2014-07-04

        DayOfWeek dayOfWeek = independenceDay.getDayOfWeek();
        System.out.println(dayOfWeek); //FRIDAY

    }


    @Test
    public void test2() {

        LocalTime now = LocalTime.now();
        System.out.println(now); //15:47:46.376

        LocalTime zero = LocalTime.of(0,0,0);
        System.out.println(zero); //00:00

        LocalTime mid = LocalTime.parse("12:00");
        System.out.println(mid); //12:00
    }

    @Test
    public void test3() {
        LocalDateTime localDateTime = LocalDateTime.of(2014,Month.MARCH,31,23,59,59);
        System.out.println(localDateTime);//2014-03-31T23:59:59

        LocalDateTime plus = localDateTime.plus(1, ChronoUnit.DAYS);
        System.out.println(plus); //2014-04-01T23:59:59
    }

    @Test
    public void test4() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime localDateTime = LocalDateTime.of(2014,Month.MARCH,31,23,59,59);
        System.out.println(localDateTime.format(formatter));
    }

    @Test
    public void testInstant() {

        Instant timestamp = Instant.now();
        System.out.println(timestamp); //2019-03-22T07:49:40.073Z
        System.out.println(timestamp.toEpochMilli()); //1553240980073
        System.out.println(System.currentTimeMillis());//1553240980137

        Instant specialTime = Instant.ofEpochMilli(timestamp.toEpochMilli());
        System.out.println(specialTime); //2019-03-22T07:49:40.073Z

    }

    @Test
    public void testDuration() {
        Duration thirtyDay = Duration.ofDays(30);
        System.out.println(thirtyDay); //PT720H
        System.out.println(thirtyDay.getSeconds()); //2592000
    }





}
