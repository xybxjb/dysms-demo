package cn.jxy;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 加鑫宇
 * @date 2020-04-10 22:57
 */

//服务的调用方
//@EnableCircuitBreaker
//@EnableDiscoveryClient
//@SpringBootApplication


@EnableFeignClients //开启feign功能
//这个相当于全导了
@SpringCloudApplication
public class ConsumerApplication {

//使用了feign 这就没用了
//    @Bean
//    @LoadBalanced  //ribbon实现客户端负载均衡
//    public RestTemplate restTemplate() {
//        return new RestTemplate();
//    }

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class);
    }
}
