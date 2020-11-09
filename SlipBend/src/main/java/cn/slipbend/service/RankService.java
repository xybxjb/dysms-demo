package cn.slipbend.service;

import cn.slipbend.util.ServerResponse;

import java.util.Map;

/**
 * @author tht
 * @version 1.0
 */
public interface RankService {
    /**
     * 我的排名
     * @param param
     * @return
     */
    ServerResponse getRank(Map<String, Object> param);

    /**
     * 本周我的行驶排名 和 本周第一名第二名的用户头像
     * @param param
     * @return
     */
    ServerResponse rankIcon(Map<String, Object> param);
}
