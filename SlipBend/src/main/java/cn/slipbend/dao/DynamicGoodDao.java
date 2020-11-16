package cn.slipbend.dao;

import cn.slipbend.model.User;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DynamicGoodDao {

    /**
     * 点赞动态
     * @param dynamicId
     * @param userId
     * @return
     */
    @Insert("insert into dynamic_good (dynamic_id, user_id) values (#{dynamicId}, #{userId})")
    Integer giveGood(Integer dynamicId, Integer userId);

    /**
     * 取消动态点赞
     * @param dynamicId
     * @param userId
     * @return
     */
    @Delete("delete from dynamic_good where dynamic_id = #{dynamicId} and user_id = #{userId}")
    Integer cancelGood(Integer dynamicId, Integer userId);

    @Select("select count(*) from dynamic_good where dynamic_id = #{dynamicId} and user_id = #{userId}")
    Integer isDoDynamicGood(Integer dynamicId, Integer userId);

    /**
     * 统计某条动态的点赞数
     * @param dynamicId
     * @return
     */
    @Select("select count(*) from dynamic_good where dynamic_id = #{dynamicId}")
    Integer countGood(Integer dynamicId);

    /**
     * 获取最近的三位点赞用户的信息
     * @param dynamicId
     * @return
     */
    @Select("select u.id,u.username,u.icon from dynamic_good dg left join user u on dg.user_id = u.id where dynamic_id = #{dynamicId} order by dg.create_time desc limit 0,3")
    List<User> lastU(Integer dynamicId);

    /**
     * 获取用户所有的获赞数
     * @param userId
     * @return
     */
    @Select("SELECT\n" +
            " COUNT(dynamic_id) countGood\n" +
            "FROM\n" +
            " dynamic_good dg\n" +
            " LEFT JOIN dynamic d ON dg.dynamic_id = d.id\n" +
            "WHERE \n" +
            " d.user_id = #{userId}")
    Integer userGoods(Integer userId);
}
