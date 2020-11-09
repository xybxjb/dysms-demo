package cn.slipbend.service;

import cn.slipbend.util.ServerResponse;

/**
 * @author tht
 * @version 1.0
 */
public interface DynamicService {
    /**
     * 发布新动态
     * @param id
     * @param text
     * @param imgsUrl
     * @param power
     * @param lon
     * @param lat
     * @return
     */
    ServerResponse releaseNews(String id, String text, String imgsUrl, String power, double lon, double lat, String locationText);

    /**
     * 动态关注
     * @param id
     * @return
     */
    ServerResponse attentions(String id);

//    ServerResponse recommend(String id);

    /**
     * 获取动态浏览量 或 动态浏览量累计
     * @param type 1获取该条动态浏览量；2累计浏览量
     * @param dynamicId 动态id
     * @return 动态浏览量
     */
    ServerResponse views(Integer type, Integer dynamicId);

    ServerResponse getDynamic(Integer dynamicId);

    /**
     * 查看我的所有动态
     * @param userId
     * @param pageSize
     * @param pageNumber
     * @return
     */
    ServerResponse getMyAllDynamic(Integer userId,Integer pageSize,Integer pageNumber);
}
