package cn.slipbend.dao;

import cn.slipbend.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: AnZX
 * @Date: 2020/07/20/18:13
 * @Description:
 */
@Repository
@Mapper
public interface LoginDao {
    /**
     * 根据手机号密码查找用户
     * @param phone
     * @param password
     * @return
     */
    @Select("SELECT * FROM user WHERE phone = #{phone} and password = #{password}")
    User findUserByPhoneAndPassword(@Param("phone") String phone, @Param("password") String password);
    /**
     * 根据手机号查找用户
     * @param phone
     * @return
     */
    @Select("SELECT * FROM user WHERE phone = #{phone}")
    User findUserByPhone(String phone);

    /**
     * 根据 微信 或 QQ 的 openId 查找用户
     * @param openIdType
     * @param openId
     * @return
     */
    User findUserByWeChatOrQQ(@Param("openIdType")Integer openIdType, @Param("openId")String openId);

    /**
     * 通过手机号注册用户
     * @param phone
     */
    @Insert("insert into user (phone) VALUES (#{phone}) ")
    void insertUser(String phone);

    /**
     * 通过 phone 以及 微信 或 QQ 的 openId | username(昵称) | icon(头像) 注册用户
     * @param openIdType
     * @param phone
     * @param openId
     * @param username
     * @param icon
     */
    void insertUserByWeChatOrQQ(@Param("openIdType")Integer openIdType, @Param("phone")String phone, @Param("openId")String openId, @Param("username")String username, @Param("icon")String icon);

    /**
     * 为已经手机号注册过的用户存入 微信 或 QQ 的 openId
     * @param phone
     * @param openId
     */
    void uptUserWeChatOrQQOpenId(@Param("openIdType")Integer openIdType,@Param("phone")String phone,@Param("openId")String openId);

    /**
     * 修改密码
     * @param phone
     * @param newPassword
     */
    @Update("UPDATE USER SET PASSWORD = #{newPassword} WHERE phone =#{phone}")
    void updatePassword(@Param("phone") String phone, @Param("newPassword") String newPassword);

    /**
     * 修改性别年龄
     * @param id
     * @param sex
     * @param age
     */
    @Update("UPDATE USER SET sex = #{sex},age = #{age} WHERE id =#{id}")
    void updateSexAndAge(@Param("id") String id, @Param("sex")String sex, @Param("age") Integer age);

    /**
     * 修改汽车和发动机型号
     * @param id
     * @param carModel
     * @param engineModel
     */
    @Update("UPDATE USER SET car_model = #{carModel},engine_model = #{engineModel} WHERE id =#{id}")
    void updateCarModel(@Param("id") String id, @Param("carModel") String carModel, @Param("engineModel") String engineModel);

    /**
     * 修改头像和用户名
     * @param id
     * @param imageUrl
     * @param username
     */
    @Update("UPDATE USER SET icon = #{imageUrl},username = #{username} WHERE id =#{id}")
    Integer updateImageAndUsername(@Param("id") String id, @Param("imageUrl") String imageUrl, @Param("username") String username);
}
