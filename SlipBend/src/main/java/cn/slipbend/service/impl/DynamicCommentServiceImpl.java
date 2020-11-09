package cn.slipbend.service.impl;

import cn.slipbend.dao.DynamicCommentDao;
import cn.slipbend.model.Comment;
import cn.slipbend.service.DynamicCommentService;
import cn.slipbend.util.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DynamicCommentServiceImpl implements DynamicCommentService {

    @Autowired
    private DynamicCommentDao dynamicCommentDao;

    @Override
    public ServerResponse postComment(Comment comment) {
        try{
            dynamicCommentDao.postComment(comment);
            return ServerResponse.getSuccess("发布评论成功，返回了该动态的总评论数",dynamicCommentDao.countComment(comment.getDynamic().getId()));
        }catch (Exception e){
            e.printStackTrace();
            return ServerResponse.getError("发布评论失败");
        }
    }

    @Override
    public ServerResponse getComment(Map<String, Object> param) {
        try{
            return ServerResponse.getSuccess("获取动态评论成功",dynamicCommentDao.getDynamicComment(param));
        }catch (Exception e){
            e.printStackTrace();
            return ServerResponse.getError("获取动态评论失败");
        }
    }

    @Override
    public ServerResponse getCommentOfComment(Map<String, Object> param) {
        try{
            return ServerResponse.getSuccess("获取评论的评论成功",dynamicCommentDao.listSubComment(param));
        }catch (Exception e){
            e.printStackTrace();
            return ServerResponse.getError("获取评论的评论失败");
        }
    }

    @Override
    public ServerResponse countComment(Integer dynamicId) {
        try{
            return ServerResponse.getSuccess("统计评论的条数成功",dynamicCommentDao.countComment(dynamicId));
        }catch (Exception e){
            e.printStackTrace();
            return ServerResponse.getError("统计评论的条数失败");
        }
    }

    /**
     * 增加评论热度 | 取消评论热度
     * @param ops 1增加评论热度；2取消评论热度
     * @param commentId 评论id
     * @param userId 用户id
     * @return 该条评论的总热度
     */
    @Override
    public ServerResponse hotOperations(Integer ops, Integer dynamicId, Integer commentId, Integer userId) {

        try{
            switch (ops){
                // 增添评论热度
                case 1:
                    if(0 == dynamicCommentDao.isGiveGood(commentId, userId)){
                        dynamicCommentDao.giveHot(dynamicId, commentId, userId);
                    }
                    break;
                // 取消评论热度
                case 2:
                    dynamicCommentDao.cancelHot(commentId, userId);
                    break;
            }

            // 返回数据
            Map<String, Object> map = new HashMap<>();
            map.put("numCommentHot", dynamicCommentDao.countHotOfComment(commentId));
            map.put("hasMyHot", dynamicCommentDao.isGiveGood(commentId, userId) == 1 ? true : false);

            return ServerResponse.getSuccess("操作成功", map);
        }catch (Exception e){
            e.printStackTrace();
            return ServerResponse.getError("操作失败");
        }
    }

    @Override
    public ServerResponse countCommentHot(Integer dynamicId) {
        try{
            return ServerResponse.getSuccess("统计评论热度成功",dynamicCommentDao.countCommentHot(dynamicId));
        }catch (Exception e){
            e.printStackTrace();
            return ServerResponse.getError("统计评论热度失败");
        }
    }

}
