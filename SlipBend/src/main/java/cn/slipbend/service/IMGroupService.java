package cn.slipbend.service;


import cn.slipbend.model.IMGroup;
import cn.slipbend.util.ServerResponse;

import java.util.List;
import java.util.Map;

public interface IMGroupService {
    /**
     * 查询圈子
     * @return
     */
    Integer selectAllIMGroup(Integer userId, String groupId);

    /**
     * 添加圈子
     * @param userId
     * @param groupId
     * @return
     */
    ServerResponse insertIMGroup(Integer userId, String groupId);

    /**
     * 删除圈子
     * @param userId
     * @param groupId
     * @return
     */

    ServerResponse deleteIMGroup(Integer userId, String groupId);

    /**
     * 根据用户id推送圈子
     * @param param
     * @return
     */
    ServerResponse selectIMGroup(Map<String,Object> param);
}
