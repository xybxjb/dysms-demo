package cn.slipbend.dao;

import cn.slipbend.model.RouteRecord;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DrivingDataDao {

    RouteRecord getMileageTimesOil(Map<String,Object> param);

    /**
     * 获取某日或某周或某月总里程记录
     * @param param
     * @return
     */
    List<RouteRecord> getRouteRecords(Map<String, Object> param);

    @Select("SELECT count(*) count, SUM(leng) leng,SUM(oil) oil FROM route_record WHERE user_id =#{id}")
    @Results(id = "tomileage", value = {
            @Result(property = "id", column = "id", id = true),
            @Result(property = "count", column = "count"),
            @Result(property = "leng", column = "leng"),
            @Result(property = "oil", column = "oil"),
    })
    RouteRecord getToMileage(@Param("id")String id);

    List<Map<String, Object>> getTmileage(Map<String, Object> param);
    Map<String, Object> getWeekTmileage(Map<String, Object> param);



}
