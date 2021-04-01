package cn.slipbend.service.impl;


import cn.slipbend.dao.IMGroupDao;
import cn.slipbend.service.IMGroupService;
import cn.slipbend.util.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class IMGroupServiceImpl implements IMGroupService {
    @Autowired
    public IMGroupDao imGroupDao;
    //查询用户圈子记录


    @Override
    public Integer selectAllIMGroup(Integer userId, String groupId) {
        return imGroupDao.selectAllIMGroup(userId,groupId);
    }

    @Override
    public ServerResponse insertIMGroup(Integer userId, String groupId) {
        try {
         if(imGroupDao.selectAllIMGroup(userId,groupId)==0){
                imGroupDao.insertIMGroup(userId,groupId);
         }
         return ServerResponse.getSuccess("添加成功");
        }catch (Exception e){
            e.printStackTrace();
            return ServerResponse.getError("ERROR");
        }
    }

    @Override
    public ServerResponse deleteIMGroup(Integer userId, String groupId) {
        try{
            imGroupDao.deleteIMGroup(userId,groupId);
            return ServerResponse.getSuccess("删除圈子成功");
        }catch (Exception e){
            e.printStackTrace();
            return ServerResponse.getError("删除圈子失败");
        }
    }
    @Override
    public ServerResponse selectIMGroup(Map<String,Object> param){
        try {
            List<String> iMGroups = null;

            String city = imGroupDao.getUserCityBy((Integer) param.get("userId"));
            if(city != null){
                param.put("city",city);
                iMGroups = imGroupDao.selectCityIMGroup(param);
            }
            else{
                iMGroups = imGroupDao.selectIMGroup(param);
            }

            return ServerResponse.getSuccess("推送圈子id数据成功", iMGroups);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.getError("推送圈子id数据失败");
        }

    }
}
