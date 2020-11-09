package cn.slipbend.service;

import cn.slipbend.model.Mode;
import cn.slipbend.model.RouteRecord;

/**
 * Created with IntelliJ IDEA.
 *
 * @Date:
 * @Description: 热度模块
 */
public interface HotService {
    /**
     * 增加赛道模式热度
     * @param modeName
     * @return
     */
    Object addModeHot(String modeName);
}
