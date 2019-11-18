//package com.leyou.test;
//
//import org.springframework.amqp.core.AmqpTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @author jiaxinyu
// * @date 2019-11-18 14:07
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class SmsTest {
//    @Autowired
//    private AmqpTemplate amqpTemplate;
//
//    @Test
//    public void testSend throwsInterruptedException() {
//        Map<String, String> msg = new HashMap<>();
//        msg.put("phone", "17812167053");
//        msg.put("code", "54321");
//        amqpTemplate.convertAndSend("ly.sms.exchange", "sms.verify.code.queue", msg);
//        Thread.sleep("10000L");
//    }
//}
