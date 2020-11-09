package cn.slipbend.config;


//import org.apache.catalina.mapper.Mapper;
//import org.mybatis.spring.mapper.MapperScannerConfigurer;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;


@Configuration
// 因为这个对象的扫描，需要在MyBatisConfig的后面注入，所以加上下面的注解
@AutoConfigureAfter(MyBatisConfig.class)

public class MyBatisMapperScannerConfig {
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        //获取之前注入的beanName为sqlSessionFactory的对象
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        //指定xml配置文件的路径
        mapperScannerConfigurer.setBasePackage("mybatis/mapper");


        //配置通用Mapper，详情请查阅官方文档
        Properties properties = new Properties();
        properties.setProperty("mappers", Mapper.class.getName());
        properties.setProperty("notEmpty", "true");//insert、update是否判断字符串类型!='' 即 test="str != null"表达式内是否追加 and str != ''
        properties.setProperty("IDENTITY", "SELECT UUID()");//使用UUID作為主鍵
        properties.setProperty("ORDER","BEFORE");//将查询主键作为前置操作
        mapperScannerConfigurer.setProperties(properties);

        return mapperScannerConfigurer;
    }

}
