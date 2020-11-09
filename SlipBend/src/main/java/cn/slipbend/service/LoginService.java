package cn.slipbend.service;

import cn.slipbend.model.User;
import cn.slipbend.util.ServerResponse;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: AnZX
 * @Date: 2020/07/20/18:09
 * @Description:
 */
public interface LoginService {
    /**
     * 手机号密码登录
     * @param phone
     * @param password
     * @return
     */
    User loginByPassword(String phone, String password);

    /**
     * 根据手机号查看用户是否注册
     * @param phone
     * @return
     */
    User findUserByPhone(String phone);

    /**
     * 根据 微信 或 QQ 的 openId 查看用户是否注册
     * @param openIdType
     * @param openId
     * @return
     */
    User findUserByWeChatOrQQ(Integer openIdType, String openId);

    /**
     * 为已经注册的用户 存入 微信 或 QQ 的 openId
     * @param openIdType
     * @param phone
     * @param openId
     */
    void uptUserWeChatOrQQOpenId(Integer openIdType, String phone,String openId);

    /**
     * 注册用户
     * @param phone
     */
    void insertUser(String phone);

    /**
     * 通过 phone 以及 微信 或 QQ 的 openId | username(昵称) | icon(头像) 注册用户
     * @param openIdType
     * @param phone
     * @param openId
     * @param username
     * @param icon
     */
    void insertUserByWeChatOrQQ(Integer openIdType, String phone,String openId, String username, String icon);

    /**
     * 修改密码
     * @param phone
     * @param newPassword
     */
    void updatePassword(String phone, String newPassword);

    /**
     * 修改性别年龄
     * @param id
     * @param sex
     * @param age
     * @return
     */
    ServerResponse updateSexAndAge(String id, String sex, Integer age);

    /**
     * 修改汽车和发动机型号
     * @param id
     * @param carModel
     * @param engineModel
     * @return
     */
    Object updateCarModel(String id, String carModel, String engineModel);

    /**
     * 修改头像和用户名
     * @param id
     * @param imageUrl
     * @param username
     * @return
     */
    ServerResponse updateImageAndUsername(String id, String imageUrl, String username);
}
