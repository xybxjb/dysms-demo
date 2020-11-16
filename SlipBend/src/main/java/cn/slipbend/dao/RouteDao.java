package cn.slipbend.dao;

import cn.slipbend.model.Mode;
import cn.slipbend.model.RouteRecord;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RouteDao {

    /**
     * 根据modeName查询mode
     * @param modeName
     * @return
     */
    @Select("SELECT * FROM mode WHERE mode_name = #{modeName}")
    Mode findModeIdByModelName(String modeName);

    /**
     * 插入用户行程记录
     * @param routeRecord
     * @return
     */
    Integer insertRouteRecord(RouteRecord routeRecord);

    /**
     * 获取赛道热度排序
     * @return
     */
    @Select("SELECT * FROM MODE ORDER BY hot DESC")
    @Results(id = "ModeMapper",value = {
            @Result(property = "id",column = "id",id = true),
            @Result(property = "modeName",column = "mode_name"),
            @Result(property = "sLongitude",column = "s_longitude"),
            @Result(property = "sLatitude",column = "s_latitude"),
            @Result(property = "eLongitude",column = "e_longitude"),
            @Result(property = "eLatitude",column = "e_latitude"),
            @Result(property = "hot",column = "hot"),
            @Result(property = "parent.id",column = "parent_id"),
            @Result(property = "user.id",column = "user_id"),
            @Result(property = "createTime",column = "create_time"),
    })
    List<Mode> findModesByHot();

    /**
     * 根据 modeId 查询两个月内热度最高的路线及用户信息
     * @param modeId 赛道id
     * @return
     */
    RouteRecord findEarliestUserAndRoute(@Param("modeId") Integer modeId,@Param("thisMonth") String thisMonth,@Param("lastMonth")String lastMonth);

    /**
     * 获取在某条赛道上竞技的人数
     * @param modeId 赛道id
     * @return
     */
    Long countNumCompetitor(Integer modeId);

    /**
     * 查看路线详情
     * @param routeId
     * @return
     */
    RouteRecord findRouteDetailById(Integer routeId);

    /**
     * 根据路线id修改心情
     * @param routeId
     * @param mood
     */
    @Update("UPDATE route_record SET mood = #{mood} WHERE id =#{routeId}")
    void updateMoodByRouteId(@Param("routeId") Integer routeId, @Param("mood") Integer mood);

    /**
     * 根据路线id添加相册
     * @param routeId
     * @param photo
     */
    @Update("UPDATE route_record SET photo = #{photo} WHERE id =#{routeId}")
    void updatePhoto(@Param("routeId") Integer routeId, @Param("photo") String photo);

    /**
     * 根据用户id查询用户完成的行程次数
     * @param userId
     * @return
     */
    @Select("select count(*) from route_record where user_id = #{userId}")
    Integer countRouteRecord(Integer userId);

    /**
     * 获取用户的百公里加速所用最短时间
     * @param userId 用户id
     * @return 百公里加速所用最短时间
     */
    @Select("select time from route_record where user_id = #{userId} and mode_id = 1 order by speed asc limit 1")
    String getTime(Integer userId);

    /**
     * 根据用户id查看该用户全部行程或相应分类下的行程
     * @param param
     * @return
     */
    List<RouteRecord> getMyRoute(Map<String, Object> param);

    /**
     * 批量删除用户行程
     * @param list routeIds
     * @return
     */
    Integer deleteRoutes(@Param("list")Integer[] list);


}
