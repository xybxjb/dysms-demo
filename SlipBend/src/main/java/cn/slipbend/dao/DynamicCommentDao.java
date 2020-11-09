package cn.slipbend.dao;

import cn.slipbend.model.Comment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DynamicCommentDao {

    /**
     * 在某条动态下发布评论或子评论
     * @param comment
     * @return
     */
    Integer postComment(Comment comment);

    /**
     * 获取动态的评论
     * @param param
     * @return
     */
    List<Comment> getDynamicComment(Map<String, Object> param);

    /**
     * 获取评论的评论
     * @param param
     * @return
     */
    List<Comment> listSubComment(Map<String, Object> param);

    /**
     * 统计某条动态下评论的条数
     * @param dynamicId 动态id
     * @return
     */
    @Select("select count(*) from dynamic_comment where dynamic_id = #{dynamicId}")
    Long countComment(Integer dynamicId);

    /**
     * 统计某条评论下的子评论的条数
     * @param pid
     * @return
     */
    Integer countSubComment(Integer pid);

    /**
     *
     * @param commentId
     * @return
     */
    Boolean hasMyHot(Integer commentId);

    /**
     * 统计某条动态下所有评论的热度
     * @param dynamicId 动态id
     * @return
     */
    @Select("select count(*) from dynamic_comment_hot where dynamic_id = #{dynamicId}")
    Long countCommentHot(Integer dynamicId);

    /**
     * 查看某个用户是否为某条评论添加了热度
     * @param commentId
     * @param userId
     * @return
     */
    Boolean hasMyHot(Integer commentId, Integer userId);

    /**
     * 查看某个用户是否为某条评论添加了热度
     * @param commentId 评论id
     * @param userId 用户id
     * @return
     */
    @Select("select count(*) from dynamic_comment_hot where comment_id = #{commentId} and user_id = #{userId}")
    Integer isGiveGood(Integer commentId, Integer userId);

    /**
     * 为某条评论增添热度
     * @param dynamicId 动态id
     * @param commentId 评论id
     * @param userId 用户id
     * @return
     */
    @Insert("insert into dynamic_comment_hot (dynamic_id, comment_id, user_id) values (#{dynamicId}, #{commentId}, #{userId})")
    Integer giveHot(Integer dynamicId, Integer commentId, Integer userId);

    /**
     * 取消增添的热度
     * @param commentId 评论id
     * @param userId 用户id
     * @return
     */
    @Delete("delete from dynamic_comment_hot where comment_id = #{commentId} and user_id = #{userId}")
    Integer cancelHot(Integer commentId, Integer userId);

    /**
     * 统计某条评论的热度
     * @param commentId 评论id
     * @return
     */
    @Select("select count(*) from dynamic_comment_hot where comment_id = #{commentId}")
    Long countHotOfComment(Integer commentId);


}
