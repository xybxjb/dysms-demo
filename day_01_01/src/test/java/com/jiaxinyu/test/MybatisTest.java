package com.jiaxinyu.test;

import com.jiaxinyu.dao.IUserDao;
import com.jiaxinyu.domin.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

/**
 * @author 加鑫宇
 * mybatis的入门案例
 */
public class MybatisTest {

    /**
     * 入门案例
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {
        //1.读取配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        //3.使用工厂生产SqlSession对象
        SqlSession session = factory.openSession();
        //4.使用SqlSession创建Dao接口的代理对象
        IUserDao userDao = session.getMapper(IUserDao.class);
        //5.使用代理对象执行方法
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
        //6.释放资源
        session.close();
        in.close();


//        InputStream in1 = Resources.getResourceAsStream("SqlMapConfig.xml");
//        SqlSessionFactoryBuilder builder1 = new SqlSessionFactoryBuilder();
//        SqlSessionFactory factory1 = builder.build(in1);
//        SqlSession session1 = factory1.openSession();
//        IUserDao userDao1 =session1.getMapper(IUserDao.class);
//        List<User> users1 = userDao1.findAll();
//        for (User user:
//             users1) {
//            System.out.println(users1);
//        }
//      session.close();
//        in1.close();
    }
}
