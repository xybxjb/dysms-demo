package cn.slipbend.dao;

import cn.slipbend.model.Mode;
import cn.slipbend.model.RouteRecord;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.sql.Time;
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
     */
    @Insert("INSERT INTO route_record(user_id,mode_id,s_longitude,s_latitude,e_longitude,e_latitude,TIME,avg_speed,speed,leng,altitude,imageUrl) VALUES (#{user.id},#{mode.id},#{sLongitude},#{sLatitude},#{eLongitude},#{eLatitude},#{time},#{avgSpeed},#{speed},#{leng},#{altitude},#{imageUrl}) ")
    void insertRouteRecord(RouteRecord routeRecord);

    /**
     * 根据modeId查找所有子mode
     * @param id
     * @return
     */
    @Select("select * from mode where parent_id=#{id}")
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
    List<Mode> findModeListByPid(Integer id);

    /**
     * 查询用户在此模式的排名
     * @param userId
     * @param modeId
     */
    @Select("SELECT MIN(名次) FROM\n" +
            "(SELECT a.mode_id,a.user_id,a.time,(@rowNum:=@rowNum+1) AS 名次\n" +
            "FROM route_record a,\n" +
            "(SELECT (@rowNum :=0) ) b WHERE a.mode_id=#{modeId}\n" +
            "ORDER BY TIME)AS c\n" +
            "WHERE user_id=#{userId}")
    Integer findModeRanking(@Param("userId") Integer userId, @Param("modeId") Integer modeId);

    /**
     * 查询用户在此模式下的最好成绩
     * @param userId
     * @param id
     * @return
     */
    @Select("SELECT TIME FROM route_record WHERE user_id=#{userId} AND mode_id=#{id} ORDER BY TIME LIMIT 0,1")
    Time findBestGrades(@Param("userId") Integer userId, @Param("id") Integer id);

    /**
     * 获取赛道热度排序
     * @return
     */
    @Select("SELECT * FROM MODE ORDER BY hot DESC")
    @ResultMap("ModeMapper")
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
    @Results(id = "RouteMapper",value = {
            @Result(property = "id",column = "id",id = true),
            @Result(property = "user.username",column = "username"),
            @Result(property = "user.icon",column = "icon"),
            @Result(property = "mode.id",column = "parent_mode_id"),
            @Result(property = "mode.modeName",column = "mode_name"),
            @Result(property = "sLongitude",column = "s_longitude"),
            @Result(property = "sLatitude",column = "s_latitude"),
            @Result(property = "eLongitude",column = "e_longitude"),
            @Result(property = "eLatitude",column = "e_latitude"),
            @Result(property = "time",column = "time"),
            @Result(property = "avgSpeed",column = "avg_speed"),
            @Result(property = "speed",column = "speed"),
            @Result(property = "leng",column = "leng"),
            @Result(property = "altitude",column = "altitude"),
            @Result(property = "hot",column = "hot"),
            @Result(property = "oil",column = "oil"),
            @Result(property = "createTime",column = "create_time"),
            @Result(property = "imageUrl",column = "imageUrl"),
            @Result(property = "mood",column = "mood"),
            @Result(property = "photo",column = "photo"),
    })
    @Select("SELECT u.username, u.icon,r.*,parentMode.mode_name,parentMode.id parent_mode_id FROM route_record r \n" +
            "INNER JOIN USER u ON r.user_id = u.id  \n" +
            "INNER JOIN MODE m ON r.mode_id = m.id\n" +
            "INNER JOIN MODE parentMode ON parentMode.id = m.parent_id\n" +
            "WHERE r.id=#{routeId}")
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

     * 获取用户的最大百公里加速所用时间
     * @param userId 用户id
     * @return 最大百公里加速所用时间
     */
    @Select("select time from route_record where user_id = #{userId} and mode_id = 1 order by speed desc limit 1")
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
