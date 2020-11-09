package cn.slipbend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
@MapperScan("cn.slipbend.dao")
public class SlipbendApplication {
	public static void main(String[] args) {
		SpringApplication.run(SlipbendApplication.class, args);
	}
}