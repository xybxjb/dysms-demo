package cn.slipbend.dao;

import cn.slipbend.model.RouteRecord;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author tht
 * @version 1.0
 */
@Repository
public interface RankDao {
    /**
     * 获取我的排名
     * @param param
     * @return
     */
    RouteRecord getMyRank(Map<String, Object> param);

    /**
     * 我的好友排名
     * @param param
     * @return
     */
    List<RouteRecord> getFriendOrCityRank(Map<String, Object> param);

    /**
     * 好友的总条数
     * @param param
     * @return
     */
    Integer totalRank(Map<String, Object> param);

    /**
     * 本周排名第一第二的头像（好友排名
     * @param param
     * @return
     */
    List<String> getFSIcon(Map<String, Object> param);

    /**
     * 我自己的本周排名数据（好友排名
     * @param param
     * @return
     */
    Integer getMyWeekRank(Map<String, Object> param);

    /**
     * 根据用户Id获取城市名称
     * @param id
     * @return
     */
    @Select("select city from user where id = #{id}")
    String getCityById(Object id);
}
