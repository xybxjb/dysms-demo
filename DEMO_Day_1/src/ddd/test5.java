package ddd;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author 加鑫宇
 * @DATE 2021/4/16 15:22
 */

public class test5 {
    public static void main(String[] args) throws ParseException {
        //键盘输入
        //创建日期类 date对象
        Date date = new Date();
//        System.out.println("现在时间：" + date);

        //格式化日期类为：yyy年MM月dd日
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Date time = (Date) sdf.format(date);
//        System.out.println("现在时间为：" + time);

        //键盘输入年月日传递给日历类set方法 设置日历字段 YEAR、MONTH 和 DAY_OF_MONTH 的值
        Calendar c = Calendar.getInstance();
        int y = c.get(Calendar.YEAR);
        int m = 3;
        int d = 16;
        //创建一个日历类对象c

        //设置日历字段 YEAR、MONTH 和 DAY_OF_MONTH 的值。
        c.set(y,m,d);
        //将日历类转化为日期类
        Date time1 = c.getTime();
        //使用SimpleDateFormat类中的format方法将日期格式化
        String format = sdf.format(time1);
        Date date2 = sdf.parse(format);
        System.out.println(time1);
        System.out.println("修改之后的时间为：" + date2);
    }
}