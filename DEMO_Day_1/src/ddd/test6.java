package ddd;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

/**
 * @author 加鑫宇
 * @DATE 2021/4/17 13:13
 */

public class test6 {
    public static void main(String[] args) {

        LocalDate localDate = LocalDate.now();
        LocalDate localDate1 = LocalDate.of(2019, 4, 16);
        Calendar c = Calendar.getInstance();

        LocalDateTime localDateTime = LocalDateTime.of(2019, Month.SEPTEMBER, 10, 14, 46, 56);
        //增加一年
        localDateTime = localDateTime.plusYears(1);
        localDateTime = localDateTime.plus(1, ChronoUnit.YEARS);


        int year = c.get(Calendar.YEAR);
        Date date2 = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");
        df.format(date2).replace("mm", "9");
        df.format(date2).replace("dd", "14");
        System.out.println(df);
    }
}
