package cn.slipbend.dao;

import cn.slipbend.model.RouteRecord;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DrivingDataDao {
    @Select("SELECT SUM(leng) leng,SUM(oil) oil,Count(*) count FROM route_record WHERE user_id =#{id} AND create_time LIKE concat(#{date},'%')")
    @Results(id = "mileage",value = {
            @Result(property = "id",column = "id",id = true),
            @Result(property = "leng",column = "leng"),
            @Result(property = "oil",column = "oil"),
            @Result(property = "count",column = "count"),
    })
    RouteRecord getMileageTimesOil(@Param("id") String id, @Param("date") String date);

    @Select("SELECT * FROM route_record WHERE user_id =#{id} AND create_time LIKE concat(#{date},'%')")
    @Results(id = "record",value = {
            @Result(property = "id",column = "id",id = true),
            @Result(property = "leng",column = "leng"),
            @Result(property = "speed",column = "speed"),
            @Result(property = "time",column = "time"),
            @Result(property = "hot",column = "hot"),
            @Result(property = "imageUrl",column = "imageUrl"),
    })
    List<RouteRecord> getRouteRecords(@Param("id") String id, @Param("date") String date);

    @Select("SELECT count(*) count, SUM(leng) leng,SUM(oil) oil FROM route_record WHERE user_id =#{id}")
    @Results(id = "tomileage", value = {
            @Result(property = "id", column = "id", id = true),
            @Result(property = "count", column = "count"),
            @Result(property = "leng", column = "leng"),
            @Result(property = "oil", column = "oil"),
    })
    RouteRecord getToMileage(@Param("id")String id);

    RouteRecord getTmileage(String id, String date,String endDate,Integer dateType);

}
