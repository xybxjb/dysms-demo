package cn.slipbend.dao;

import cn.slipbend.model.Mode;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModeDao {

    /**
     * 获取赛道模式一级菜单
     * @return
     */
    @Select("select * from mode where parent_id = 0")
    @Results(id = "modeResult",value = {
            @Result(property = "id",column = "id",id = true),
            @Result(property = "modeName",column = "mode_name"),
            @Result(property = "sLongitude",column = "s_longitude"),
            @Result(property = "sLatitude",column = "s_latitude"),
            @Result(property = "eLongitude",column = "e_longitude"),
            @Result(property = "eLatitude",column = "e_latitude"),
            @Result(property = "hot",column = "hot"),
            @Result(property = "parent.id",column = "parent_id"),
            @Result(property = "createTime",column = "create_time")
    })
    List<Mode> modeTop();

    /**
     * 获取赛道模式二级菜单
     * @parentId 父级id
     * @return
     */
    @Select("select * from mode where parent_id = #{parentId}")
    @ResultMap("modeResult")
    List<Mode> modeSecond(Integer parentId);


    /**
     * 查询用户此模式的排名和最好成绩
     * @param userId
     * @param parentId
     * @return
     */
//    @Select("select * from mode where parent_id = #{parentId}")
//    @Results(id = "modeResult",value = {
//            @Result(property = "id",column = "id",id = true),
//            @Result(property = "modeName",column = "mode_name"),
//            @Result(property = "sLongitude",column = "s_longitude"),
//            @Result(property = "sLatitude",column = "s_latitude"),
//            @Result(property = "eLongitude",column = "e_longitude"),
//            @Result(property = "eLatitude",column = "e_latitude"),
//            @Result(property = "hot",column = "hot"),
//            @Result(property = "parent.id",column = "parent_id"),
//            @Result(property = "user.id",column = "user_id"),
//            @Result(property = "createTime",column = "create_time")
//    })
    List<Mode> findModeHotAndRank(Integer userId,Integer parentId);

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
    Integer findModeRanking(@Param("userId") Integer userId, @Param("modeId") Integer modeId);

    /**
     * 查询用户在此模式下的最好成绩
     * @param modeId
     * @return
     */
    String findBestGrades(@Param("modeId") Integer modeId);
}
