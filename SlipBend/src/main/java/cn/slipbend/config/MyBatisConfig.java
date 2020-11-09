package cn.slipbend.config;

import cn.slipbend.handler.MyTypeHandler;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;

import java.io.IOException;

import static org.springframework.core.io.support.PathMatchingResourcePatternResolver.*;

@Configuration
//加上这个注解，使得支持事务
@EnableTransactionManagement
public class MyBatisConfig implements TransactionManagementConfigurer {
    @Autowired
    private DataSource dataSource;

    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        //bean.setConfigLocation(new ClassPathResource("")); //  扫描mybatis配置文件
        //bean.setConfiguration(new org.apache.ibatis.session.Configuration( ).set);
        bean.setTypeAliasesPackage("cn.slipbend.model");
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        String packageSearchPath = PathMatchingResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + "mybatis/mapper/*.xml";
        bean.setMapperLocations(resolver.getResources(packageSearchPath));
        //bean.setTypeHandlersPackage("cn.classify.enums");


        // 取得类型转换注册器
        TypeHandlerRegistry typeHandlerRegistry = bean.getObject().getConfiguration().getTypeHandlerRegistry();
        // 注册默认枚举转换器
        typeHandlerRegistry.setDefaultEnumTypeHandler(MyTypeHandler.class);
        try {
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
