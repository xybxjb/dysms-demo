package cn.slipbend.service;

import cn.slipbend.model.User;
import cn.slipbend.util.ServerResponse;

/**
 * Created with IntelliJ IDEA.
 *
 * @Date:
 * @Description: 用户操作
 */
public interface UserService {
    /**
     * 修改用户个人资料
     * @param user
     * @return
     */
    Object updateUserInfo(User user);

    /**
     * 修改车辆信息
     * @param id
     * @param carModel
     * @param carAge
     * @param engineModel
     * @return
     */
    Object updateCarInfo(Integer id, String carModel, Integer carAge, String engineModel);

    /**
     * 计算用户行驶总距离
     * @param id
     * @return
     */
    Object findUserDrivingLength(Integer id);

    /**
     * 用户行驶到达一系列的公里数的相应日期
     * @param userId
     * @param mileage
     * @return
     */
    ServerResponse toLevelDate(Integer userId, String[] mileage);

    /**
     * 查找达成里程成就的人数
     * @param mileage
     * @return
     */
    Object findCompletePeopleByMileage(Double mileage);

    /**
     * 查看用户关注列表
     * @param id
     * @return
     */
    Object findUserFollow(Integer id);
    /**
     * 查看用户粉丝列表
     * @param id
     * @return
     */
    Object findUserFans(Integer id);

    /**
     * 查看用户个人信息
     * @param id
     * @return
     */
    Object findUserInfo(Integer id, Boolean isBaseInfo);

    /**
     * 修改第三方注释状态
     * @param userId
     * @return
     */
    ServerResponse updateSignInWith(Integer userId,Integer weChatStatus,Integer qqStatus);

    /**
     * 注销账户
     * @param userId
     * @return
     */
    ServerResponse deleteAccount(Integer userId);

    /**
     * 获取第三方登录状态
     * @param userId
     * @return
     */
    ServerResponse getSignInWith(Integer userId);

    ServerResponse updateAttention(Integer userId, Integer noAttentionStatus);

    ServerResponse getAttention(Integer userId);

    ServerResponse updateNearby(Integer userId, Integer nearbyStatus);

    /**
     * 获取是否出现附近状态
     * @param userId
     * @return
     */
    ServerResponse getSignNearby(Integer userId);

    /**
     * 修改背景图片
     * @param userId
     * @param backgroudImage
     * @return
     */

    ServerResponse updatebackgroundImage(Integer userId, String backgroudImage);
}
