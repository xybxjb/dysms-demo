package cn.slipbend.dao;

import cn.slipbend.model.RouteRecordMark;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteMarkDao {

    /**
     * 插入用户行程记录产生的标记点
     * @param routeRecordMark
     * @return
     */
    Integer insertRouteMark(RouteRecordMark routeRecordMark);

    /**
     * 查询某条行程记录的所有标记点位
     * @param routeId 行程记录id
     * @return
     */
    List<RouteRecordMark> selectRouteMarks(Integer routeId);
}
