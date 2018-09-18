package com.atguigu.springboot.tlife.healthRecord.constant;

import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * @author hike97
 * @create 2018-09-17 16:30
 * @desc 测试
 **/
public class Test {
    public static void main(String[] args) {
        LocalDate newLocalDate = LocalDate.of(2017, 6, 21).minusWeeks(1l).with( DayOfWeek.MONDAY);
    }
}
