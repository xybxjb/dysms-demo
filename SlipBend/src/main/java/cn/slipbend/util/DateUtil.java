package cn.slipbend.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    /**
     * 获取指定年月日的前n月（年|日）或后n月（年|日）
     *
     * 使用示例：
     * getLastMonth("2011-06",0,-1,0);//2011
     * getLastMonth("2011-06",0,-6,0);//2010-1205
     * getLastMonth("2011-06",-1,0,0);//2010-06
     *
     * getLastDate("2011-06-03",0,-5);//2010-05-29
     *
     * @param dateStr 格式示例：yyyy-MM 或 yyyy-MM-yy
     * @param addYear
     * @param addMonth
     * @param addDate
     * @return 输入的时期格式为yyyy-MM 或 yyyy-MM-yy，输出的日期格式为yyyy-MM 或 yyyy-MM-yy
     * @throws Exception
     */
    public static String getLastDate(String dateStr,int addYear, int addMonth, int addDate) throws Exception {
        try {
            SimpleDateFormat sdf = createSimpleDateFormat(dateStr);

            Date sourceDate = sdf.parse(dateStr);
            Calendar cal = Calendar.getInstance();
            cal.setTime(sourceDate);
            cal.add(Calendar.YEAR,addYear);
            cal.add(Calendar.MONTH, addMonth);
            cal.add(Calendar.DATE, addDate);

            SimpleDateFormat returnSdf = createSimpleDateFormat(dateStr);
            String dateTmp = returnSdf.format(cal.getTime());
//            Date returnDate = returnSdf.parse(dateTmp);
            return dateTmp;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    private static SimpleDateFormat createSimpleDateFormat(String dateStr){
        java.text.SimpleDateFormat sdf = null;
        if(dateStr != null && dateStr.length() == 10){
            sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        }
        if(dateStr != null && dateStr.length() == 7){
            sdf = new java.text.SimpleDateFormat("yyyy-MM");
        }
        return sdf;
    }

    /**
     * 获取某年某月有多少天
     * @param date 年月 格式示例：2020-05
     * @return
     */
    public static int getDayOfMonth(String date){
        Integer year = Integer.parseInt(date.substring(0,4));
        Integer month = Integer.parseInt(date.substring(5,7));
        Calendar c = Calendar.getInstance();
        //输入类型为int类型
        c.set(year, month, 0);
        return c.get(Calendar.DAY_OF_MONTH);
    }

    public static String getTimeInterval(Date date) {
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        // System.out.println("要计算日期为:" + sdf.format(cal.getTime())); // 输出要计算日期
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        String imptimeBegin = sdf.format(cal.getTime());
        // System.out.println("所在周星期一的日期：" + imptimeBegin);
        cal.add(Calendar.DATE, 6);
        String imptimeEnd = sdf.format(cal.getTime());
        // System.out.println("所在周星期日的日期：" + imptimeEnd);
        return imptimeBegin + "," + imptimeEnd;
    }
}
