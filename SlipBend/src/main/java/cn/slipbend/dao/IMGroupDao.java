package cn.slipbend.dao;

import cn.slipbend.model.IMGroup;
import cn.slipbend.util.ServerResponse;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface IMGroupDao {

    @Select("SELECT COUNT(*) FROM im_group WHERE user_id = #{userId} AND group_id = #{groupId}")
    Integer selectAllIMGroup(Integer userId, String groupId);


    @Insert("insert into im_group (user_id, group_id) values (#{userId}, #{groupId})")
    Integer insertIMGroup(Integer userId, String groupId);
    @Delete("DELETE FROM im_group WHERE im_group.user_id = #{userId} and im_group.group_id = #{groupId}")
    Integer deleteIMGroup(Integer userId, String groupId);

    @Select("select distinct group_id from im_group\n" +
            "WHERE group_id NOT in(SELECT group_id from im_group WHERE user_id = #{userId}) LIMIT #{a},#{b}")
    List<String> selectIMGroup(Map<String, Object> param);

    @Select("SELECT\n" +
            "\tdistinct img.group_id \n" +
            "FROM\n" +
            "\tim_group img\n" +
            "\tINNER JOIN `user` u ON img.user_id = u.id\n" +
            "WHERE\n" +
            "\tu.city = #{city}\n" +
            "\tAND img.group_id NOT in(SELECT group_id from im_group WHERE user_id = #{userId}) LIMIT #{a},#{b}")
    List<String> selectCityIMGroup(Map<String, Object> param);

    /**
     * 根据用户id查询用户所在的城市
     * @param userId 用户id
     * @return 城市的行政区划编码
     */
    @Select("select city from user where id = #{userId}")
    String getUserCityBy(Integer userId);
}
