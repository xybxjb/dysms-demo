package cn.jxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author 加鑫宇
 * @date 2020-04-10 21:59
 */
//服务的提供方
@SpringBootApplication
@MapperScan("cn.jxy.user.mapper")
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class);
    }
}
