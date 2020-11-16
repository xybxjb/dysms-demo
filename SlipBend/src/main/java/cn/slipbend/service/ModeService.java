package cn.slipbend.service;

import cn.slipbend.util.ServerResponse;

public interface ModeService {

    /**
     * 获取赛道模式一级菜单
     * @return
     */
    ServerResponse modeTop();

    /**
     * 获取赛道模式二级菜单
     * @param modeId
     * @return
     */
    ServerResponse modeSecond(Integer modeId);

    /**
     * 查询用户此模式的排名和最好成绩
     * @param userId
     * @param modeId
     * @return
     */
    ServerResponse findModeHotAndRank(Integer userId, Integer modeId);


}
