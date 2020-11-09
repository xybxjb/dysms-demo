package cn.slipbend.service;

import cn.slipbend.model.Comment;
import cn.slipbend.util.ServerResponse;

import java.util.Map;

public interface DynamicCommentService {

    /**
     * 在某条动态下发布评论或子评论
     * @param comment
     * @return
     */
    ServerResponse postComment(Comment comment);

    /**
     * 获取动态评论
     * @param param
     * @return
     */
    ServerResponse getComment(Map<String, Object> param);

    /**
     * 获取评论的评论
     * @param param
     * @return
     */
    ServerResponse getCommentOfComment(Map<String, Object> param);

    /**
     * 统计某条动态下评论的条数
     * @param dynamicId
     * @return
     */
    ServerResponse countComment(Integer dynamicId);

    /**
     * 增加 或 取消 评论热度
     * @param ops 1增加评论热度；2取消评论热度;
     * @param dynamicId 动态id
     * @param commentId 评论id
     * @param userId 用户id
     * @return 该评论热度的数值
     */
    ServerResponse hotOperations(Integer ops, Integer dynamicId, Integer commentId, Integer userId);

    /**
     * 统计某条动态下评论的热度
     * @param dynamicId
     * @return
     */
    ServerResponse countCommentHot(Integer dynamicId);

}
