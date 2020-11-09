package cn.slipbend.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface RouteHotDao {

    /**
     * 当前用户是否为该条行程增添了热度
     * @param routeId
     * @param userId
     * @return
     */
    Boolean hasMyHot(Integer routeId, Integer userId);

    /**
     * 增加行程热度
     * @param routeId
     * @param userId
     * @return
     */
    Integer giveHot(Integer routeId, Integer userId);

    /**
     * 取消行程热度
     * @param routeId
     * @param userId
     * @return
     */
    Integer cancelHot(Integer routeId, Integer userId);

    /**
     * 该条行程的热度
     * @param routeId
     * @return
     */
    Long countRouteHot(Integer routeId);
}
