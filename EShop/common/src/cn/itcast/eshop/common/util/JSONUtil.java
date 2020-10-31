package cn.itcast.eshop.common.util;

import cn.itcast.eshop.common.entity.Entity;
import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;


/**
 * JSON工具类
 * 处理和JSON相关的所有内容
 */
public class JSONUtil {

    public static void main(String[] args) {
        Entity entity = new Entity();
        entity.setId("100");
        entity.setCreateTime("18:16");
        String json = entity2JSON(entity);
        System.out.println(json); // {"createTime":"18:16","id":"100","idDel":"1"}

        System.out.println("----------------");
        List<Entity> entityList = new ArrayList<>();
        entityList.add(entity);
        String jsonList = entityList2JSON(entityList);
        System.out.println(jsonList); // [{"createTime":"18:16","id":"100","idDel":"1"}]

        System.out.println("----------------");
//        String jsonStr = "{\"createTime\":\"18:25\",\"id\":\"100\",\"idDel\":\"1\"}";
//        Object obj = JSON2Entity(jsonStr, Entity.class);
//        Entity e = (Entity)obj;
//        System.out.println(e.getCreateTime());

        System.out.println("----------------");
        String jsonStr = "{\"createTime\":\"18:28\",\"id\":\"100\",\"idDel\":\"1\"}";
        Entity e = JSON2Entity(jsonStr, Entity.class);
        System.out.println(e.getCreateTime());

        System.out.println("----------------");
        String jsonArr = "[{\"createTime\":\"18:30\",\"id\":\"100\",\"idDel\":\"1\"}, {\"createTime\":\"18:35\",\"id\":\"100\",\"idDel\":\"1\"}]";
        List<Entity> el = JSONArray2List(jsonArr, Entity.class);
        System.out.println(el.get(0).getCreateTime());
        System.out.println(el.get(1).getCreateTime());

//        User user = new User();
//        user.setUsername("xiaohei");
//        user.setPassword("xiaohei");
//        User user2 = new User();
//        user2.setUsername("admin");
//        user2.setPassword("123456");
//        user2.setRole(User.ADMIN);
//        List<User> userList = new ArrayList<>();
//        userList.add(user);
//        userList.add(user2);
//        String userListJson = entityList2JSON(userList);
//        System.out.println(userListJson);
    }

    /**
     * 把对象转换成JSON格式的字符串
     *
     * @param entity 指定对象
     * @return 返回JSON格式的字符串
     */
    public static String entity2JSON(Object entity) {
        return JSON.toJSONString(entity);
    }

    /**
     * 把对象列表转换成JSON格式的字符串
     *
     * @param entityList 指定对象列表
     * @return 返回JSON格式的字符串
     */
    public static String entityList2JSON(List<?> entityList) {
        return entity2JSON(entityList);
    }

    /**
     * 把JSON字符串转换成指定类型的对象
     *
     * ? 泛型的通配符，代表的是未知的任意类型，或者说是Object
     * @param json 要转换的数据
     * @param clazz 指定的类型
     * @return 返回Object对象
     */
//    public static Object JSON2Entity(String json, Class<?> clazz) {
//       Object obj = JSON.parseObject(json, clazz);
//       return obj;
//    }

    public static <T> T JSON2Entity(String json, Class<T> clazz) {
        return JSON.parseObject(json, clazz);
    }

    /**
     * 将JSON数组转换成指定类型的对象列表
     *
     * @param json 数据
     * @param clazz 指定的类型Class对象
     * @param <T> 指定的类型
     * @return 对象列表
     */
    public static <T> List<T> JSONArray2List(String json, Class<T> clazz) {
        return JSON.parseArray(json, clazz);
    }

//    public static List<?> JSONArray2List(String json, Class<?> clazz) {
//        return JSON.parseArray(json, clazz);
//    }

}
