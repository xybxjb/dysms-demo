package cn.slipbend.service;

import cn.slipbend.util.ServerResponse;

public interface DynamicGoodService {

    /**
     * 点赞动态 | 取消点赞动态 | 查看是否点赞某条动态
     * @param ops 操作：1点赞动态；2取消点赞动态；3查看是否点赞某条动态
     * @param dynamicId 动态id
     * @param userId 用户id
     * @return 当前用户与该条动态的关注关系、该条动态的总点赞数、该条动态的最近点赞的前三个用户的信息
     */
    ServerResponse operations(Integer ops, Integer dynamicId, Integer userId);

}
