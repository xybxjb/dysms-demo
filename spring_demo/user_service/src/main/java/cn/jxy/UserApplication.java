package cn.jxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author 加鑫宇
 * @date 2020-04-10 21:59
 */
//服务的提供方
@EnableDiscoveryClient  //让注册中心发现，扫描到该服务
@SpringBootApplication
@MapperScan("cn.jxy.user.mapper") //通用mapper
class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class);
    }
}
