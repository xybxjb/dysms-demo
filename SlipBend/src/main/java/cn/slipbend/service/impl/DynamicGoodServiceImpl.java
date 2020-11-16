package cn.slipbend.service.impl;

import cn.slipbend.dao.DynamicGoodDao;
import cn.slipbend.service.DynamicGoodService;
import cn.slipbend.util.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DynamicGoodServiceImpl implements DynamicGoodService {

    @Autowired
    private DynamicGoodDao dynamicGoodDao;

    /**
     * 点赞动态 | 取消点赞动态 | 查看是否点赞某条动态
     * @param ops 操作：1点赞动态；2取消点赞动态；3查看是否点赞某条动态
     * @param dynamicId 动态id
     * @param userId 用户id
     * @return
     */
    @Override
    public ServerResponse operations(Integer ops, Integer dynamicId, Integer userId) {
        try{
            switch (ops){
                // 点赞动态
                case 1:
                    if(0 == dynamicGoodDao.isDoDynamicGood(dynamicId, userId)){
                        dynamicGoodDao.giveGood(dynamicId,userId);
                    }
                    break;
                // 取消动态点赞
                case 2:
                    dynamicGoodDao.cancelGood(dynamicId,userId);
                    break;
            }
            return ServerResponse.getSuccess("操作成功", dynamicGoodStatus(dynamicId,userId));
        }catch (Exception e){
            e.printStackTrace();
            return ServerResponse.getError("操作失败");
        }
    }


    private Map<String, Object> dynamicGoodStatus(Integer dynamicId, Integer userId){
        Map<String, Object> result = new HashMap<>();
        // 查看 当前用户 对 某条动态 是否 点赞
        result.put("hasMyGood", dynamicGoodDao.isDoDynamicGood(dynamicId, userId) == 1 ? true : false);
        // 统计点赞数
        result.put("numGood", dynamicGoodDao.countGood(dynamicId));
        // 获得最近点赞的用户信息（前3个）
        result.put("lastU", dynamicGoodDao.lastU(dynamicId));
        return result;
    }


    @Override
    public ServerResponse userGooods(Integer userId) {

        return  ServerResponse.getSuccess("获取数据成功",dynamicGoodDao.userGoods(userId));
    }
}
