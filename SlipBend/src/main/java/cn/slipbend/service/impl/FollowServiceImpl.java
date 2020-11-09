package cn.slipbend.service.impl;

import cn.slipbend.dao.FollowDao;
import cn.slipbend.enums.RelationshipEnum;
import cn.slipbend.service.FollowService;
import cn.slipbend.util.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowServiceImpl implements FollowService {

    @Autowired
    private FollowDao followDao;

    @Override
    public ServerResponse follow(Integer ops, Integer userId, Integer targetUserId) {
        try{
            Boolean isMyFollow = 1 == followDao.findFollow(targetUserId,userId);

            // 加关注
            if(1 == ops){
                if(!isMyFollow){
                    followDao.addFollow(userId, targetUserId);
                }
            }
            // 取消关注
            if(2 == ops){
                followDao.delFollow(userId, targetUserId);
            }

            // 查看当前用户是否关注了某个用户
            RelationshipEnum followResult;
            isMyFollow = 1 == followDao.findFollow(targetUserId,userId);
            if(isMyFollow){
                followResult = RelationshipEnum.FOLLOWED;
            }
            else{
                followResult = RelationshipEnum.NO_FOLLOW;
            }

            return ServerResponse.getSuccess("操作成功",followResult);
        }catch (Exception e){
            e.printStackTrace();
            return ServerResponse.getError("操作失败");
        }
    }

}
