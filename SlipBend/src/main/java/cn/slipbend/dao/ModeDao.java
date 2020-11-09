package cn.slipbend.dao;

import cn.slipbend.model.Mode;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
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

}
