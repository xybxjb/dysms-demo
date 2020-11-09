package cn.slipbend.service;

import cn.slipbend.util.ServerResponse;

public interface NearService {

    /**
     * 保存用户位置
     * @param userId
     * @param longitude
     * @param latitude
     * @return
     */
    ServerResponse saveUserPosition(Integer userId, Double longitude, Double latitude);

    /**
     * 删除用户位置
     * @param userId
     * @return
     */
    ServerResponse deleteUserPosition(Integer userId);

    /**
     * 查找附近用户
     * @param longitude
     * @param latitude
     * @param distance
     * @param count
     * @return
     */
    ServerResponse nearByXY(Double longitude, Double latitude, Integer distance, Integer count);

    ServerResponse slideToLeft(Integer userId, Integer slideLeftUserId);

    ServerResponse slideToRight(Integer userId, Integer slideRightUserId);

    ServerResponse userInfo(Integer id);

    ServerResponse geoDist(Object member1, Object member2);
    ServerResponse getUserPosition(Integer userId);
}
