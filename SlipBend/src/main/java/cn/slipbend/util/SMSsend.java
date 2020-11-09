package cn.slipbend.util;

import org.apache.http.HttpResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SMSsend {
    private static String host = "http://yzx.market.alicloudapi.com";
    private static String path = "/yzx/sendSms";
    private static String method = "POST";
    private static String appcode = "b66808e1a429494795dff816cde16ecc";
    private static int LENGTH = 6;

    public static StringBuffer _code;

    static {
        _code = new StringBuffer();
        _code.setLength(LENGTH);
    }

    public static void send(String phone) {
        _code.setLength(0);
        //随机码
        int code[] = new int[LENGTH];
        Random random = new Random();
        for (int i = 0;i<6;i++) {
            code[i] = random.nextInt(10);
            _code.append(code[i]);
        }
        //消息头格式
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        //发送方式
        Map<String, String> querys = new HashMap<String, String>();
//        querys.put("mobile", "15905351199");
        querys.put("mobile", phone);
        querys.put("param", "code:"+_code);
        querys.put("tpl_id", "TP1710262");
        Map<String, String> bodys = new HashMap<String, String>();
        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());
            //获取response的body
            //System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
