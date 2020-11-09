package cn.slipbend.service;

import cn.slipbend.util.ServerResponse;

public interface FollowService {

    /**
     * 添加 / 取消 关注 或者 查看当前用户是否关注了某个用户
     * @param ops 默认查看当前用户是否关注了某个用户(即不传入值)；1加关注；2取消关注
     * @param userId 登录用户id
     * @param targetUserId 目标用户id
     * @return 当前用户是否关注了某个用户 的 结果
     */
    ServerResponse follow(Integer ops, Integer userId, Integer targetUserId);


}
