package cn.slipbend.dao;

import cn.slipbend.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    /**
     * 根据id修改用户信息
     * @param user
     */
   @Update("<script>"+
           "update user set\n" +
           "\t\t<trim suffixOverrides=\",\">\n" +
           "\t\t\t<if test=\"username !=null and username != ''\"> username=#{username}, </if>\n" +
           "\t\t\t<if test=\"sex !=null and sex != ''\"> sex=#{sex}, </if>\n" +
           "\t\t\t<if test=\"age !=null and age != ''\"> age=#{age}, </if>\n" +
           "\t\t\t<if test=\"hobby !=null and hobby != ''\"> hobby=#{hobby}, </if>\n" +
           "\t\t\t<if test=\"icon !=null and icon != ''\"> icon=#{icon}, </if>\n" +
           "\t\t\t<if test=\"city.num !=null and city.num != ''\"> city=#{city.num}, </if>\n" +
           //"\t\t\t<if test=\"area.num !=null and area.num != ''\"> area_Num=#{area.num}, </if>\n" +
           "\t\t</trim>\n" +
           "\t\twhere id=#{id}"+
           "</script>")
    void updateUserInfoById(User user);

    /**
     * 根据用户id修改车辆信息
     * @param id
     * @param carModel
     * @param carAge
     * @param engineModel
     */
    @Update("<script>"+
            "update user set\n" +
            "\t\t<trim suffixOverrides=\",\">\n" +
            "\t\t\t<if test=\"carModel !=null and carModel != ''\"> car_model=#{carModel}, </if>\n" +
            "\t\t\t<if test=\"carAge !=null and carAge != ''\"> car_age=#{carAge}, </if>\n" +
            "\t\t\t<if test=\"engineModel !=null and engineModel != ''\"> engine_model=#{engineModel}, </if>\n" +
            "\t\t</trim>\n" +
            "\t\twhere id=#{id}"+
            "</script>")
    void updateCarInfo(@Param("id") Integer id, @Param("carModel") String carModel, @Param("carAge") Integer carAge, @Param("engineModel") String engineModel);

    /**
     * 根据用户id获取用户车辆信息
     * @param userId 用户id
     * @return
     */
    @Select("select car_model carModel,engine_model engineModel,car_age carAge from user where id = #{userId}")
    User getCarInfo(Integer userId);

    /**
     * 根据id查询用户
     * @param user
     * @return
     */
    @Select("SELECT * FROM user WHERE id = #{id}")
    User findUserById(User user);

    /**
     * 计算用户行驶总距离
     * @param id
     * @return
     */
    @Select("SELECT SUM(leng) FROM route_record WHERE user_id=#{id}")
    Integer findUserAllLength(Integer id);

    /**
     * 根据里程公里数查看达成里程成就的人数
     * @param mileage
     * @return
     */
    @Select("SELECT COUNT(*) FROM (SELECT SUM(leng) allleng FROM route_record GROUP BY user_id) AS a WHERE allleng>=#{mileage}")
    Integer findCompletePeopleByMileage(Double mileage);

    /**
     * 查询用户行驶到一定公里数的日期
     * @param userId
     * @param mileage
     * @return
     */
    @Select("SELECT\n" +
            "\tcreate_time\n" +
            "FROM(\n" +
            "\tSELECT\n" +
            "\t\tcreate_time,\n" +
            "\t\tleng,\n" +
            "\t\t@total := @total + leng AS 'total_leng' \n" +
            "\tFROM\n" +
            "\t\t( SELECT id, create_time, leng FROM route_record WHERE user_id = #{userId} ORDER BY create_time) AS temp,\n" +
            "\t\t( SELECT @total := 0 ) AS T1 \n" +
            ") AS TEMP\n" +
            "WHERE \n" +
            "\ttotal_leng >= #{mileage}\n" +
            "LIMIT 1")
    String getToLevelDate(Integer userId, String mileage);

    /**
     * 查看用户关注列表
     * @param id
     * @return
     */
    @Select("SELECT user.`id`,user.`username`,user.`icon` FROM follow INNER JOIN USER ON follow.`follow_user_id`=user.`id` WHERE fans_user_id=#{id}")
    List<User> findUserFollow(Integer id);

    /**
     * 查看用户粉丝列表
     * @param id
     * @return
     */
    @Select("SELECT user.`id`,user.`username`,user.`icon` FROM follow INNER JOIN USER ON follow.`fans_user_id`=user.`id` WHERE follow_user_id=#{id}")
    List<User> findUserFans(Integer id);

   /**
    * 查询用户行程次数
    * @param id
    * @return
    */
   @Select("SELECT COUNT(*) FROM route_record WHERE user_id = #{id}")
   Integer findCountRoute(Integer id);

   /**
    * 查询用户关注数
    * @param id
    * @return
    */
   @Select("SELECT COUNT(*) FROM follow WHERE fans_user_id = #{id}")
   Integer findCountFollow(Integer id);

   /**
    * 查询用户粉丝数
    * @param id
    * @return
    */
   @Select("SELECT COUNT(*) FROM follow WHERE follow_user_id=#{id}")
   Integer findCountFans(Integer id);

   /**
    * 查询用户动态数
    * @param id
    * @return
    */
   @Select("SELECT COUNT(*) FROM DYNAMIC WHERE user_id =#{id}")
   Integer findCountDynamic(Integer id);

    /**
     * 查询用户总油耗
     * @param id
     * @return
     */
    @Select("SELECT SUM(oil) FROM route_record WHERE user_id = #{id}")
    Integer findUserAllOil(Integer id);

    /**
     * 根据用户id查询用户基础信息
     * @param userId 用户id
     * @return
     */
    @Select("SELECT u.id,u.username,u.phone,u.icon,u.sex,u.age,u.car_model carModel,u.car_age carAge,u.engine_model engineModel,u.hobby,a.name area_name,a.num area_num FROM user u left join area a on u.city = a.num WHERE u.id = #{userId}")
    @Results(id = "userResult",value = {
            @Result(property = "id",column = "id",id = true),
            @Result(property = "city.name",column = "area_name"),
            @Result(property = "city.num",column = "area_num"),
    })
    User findUserBaseInfoBy(Integer userId);

    /**
     * 是否允许第三方登录
     * @param userId
     * @param weChatStatus
     * @param qqStatus
     */
    @Update("<script>"+
            "update user set\n" +
            "\t\t<trim suffixOverrides=\",\">\n" +
            "\t\t\t<if test=\"weChatStatus !=null\"> isYWeChat=#{weChatStatus}, </if>\n" +
            "\t\t\t<if test=\"qqStatus !=null\"> isYQQ=#{qqStatus}</if>\n" +
            "\t\t</trim>\n" +
            "\t\twhere id=#{userId}"+
            "</script>")
    Integer updateSignInWith(Integer userId, Integer weChatStatus, Integer qqStatus);

    /**
     * 注销账户
     * @param userId
     */
    @Delete("DELETE a,b,c,d,e,f,g,h,i,j FROM `user` a\n" +
            "LEFT JOIN route_record b\n" +
            "ON a.`id`=b.`user_id`\n" +
            "LEFT JOIN right_slider c\n" +
            "ON a.`id`=c.`user_id`\n" +
            "LEFT JOIN right_slider d\n" +
            "ON a.`id`=d.`cover_user_id`\n" +
            "LEFT JOIN left_slider e\n" +
            "ON a.`id`=e.`user_id`\n" +
            "LEFT JOIN left_slider f\n" +
            "ON a.`id`=f.`cover_user_id`\n" +
            "LEFT JOIN dynamic_good g\n" +
            "ON a.`id`=g.`user_id`\n" +
            "LEFT JOIN dynamic_good h\n" +
            "ON a.`id`=h.`dynamic_id`\n" +
            "LEFT JOIN follow i\n" +
            "ON a.`id`=i.`follow_user_id`\n" +
            "LEFT JOIN follow j\n" +
            "ON a.`id`=j.`fans_user_id`\n" +
            "WHERE a.id=#{userId}")
    Integer deleteAccount(@Param("userId") Integer userId);

    /**
     * 获取第三方登录状态
     * @param userId
     * @return
     */
    @Select("SELECT isYWeChat,isYQQ FROM USER WHERE id =#{userId}")
    User getSignInWith(Integer userId);
/**
 * 是否出现附近
 * @param userId
 * @param nearbyStatus
 * */
@Update("update user set nearby = #{nearbyStatus} where id=#{userId}")
    Integer updateNearby(Integer userId, Integer nearbyStatus);
    /**
     * 获取出现在附近的人中状态
     * @param userId
     * @return
     */

    /**
     * 修改是否接收未关注对象的消息
     */
    @Update("update user set no_attention = #{noAttentionStatus} where id=#{userId}")
    Integer updateAttention(Integer userId, Integer noAttentionStatus);

    /**
     * 获取接收未关注对象的状态
     * @param userId
     * @return
     */
    @Select("SELECT no_attention FROM `user` WHERE id = #{userId}")
    Integer getAttention(Integer userId);

    @Select("select nearby from user where id=#{userId}")
    Integer getSignNearby(Integer userId);

}
