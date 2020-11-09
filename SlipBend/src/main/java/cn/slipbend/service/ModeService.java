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

}
