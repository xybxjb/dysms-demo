package cn.slipbend.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowDao {

    /**
     * 加关注
     * @param userId
     * @param followUserId
     * @return
     */
    @Insert("INSERT INTO follow (follow_user_id,fans_user_id) VALUES (#{followUserId},#{userId})")
    Integer addFollow(Integer userId, Integer followUserId);

    /**
     * 取消关注
     * @param userId
     * @param targetUserId
     * @return
     */
    @Select("delete from follow where fans_user_id = #{userId} and follow_user_id = #{targetUserId}")
    Integer delFollow(Integer userId, Integer targetUserId);

    /**
     * 统计某个人是否关注了某个人
     * @param followUserId
     * @param fansUserId
     * @return
     */
    @Select("SELECT COUNT(id) FROM follow WHERE follow_user_id = #{followUserId} and fans_user_id = #{fansUserId}")
    Integer findFollow(Integer followUserId, Integer fansUserId);
}
