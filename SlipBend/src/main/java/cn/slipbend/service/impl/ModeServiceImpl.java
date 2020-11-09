package cn.slipbend.service.impl;

import cn.slipbend.dao.ModeDao;
import cn.slipbend.service.ModeService;
import cn.slipbend.util.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author RS
 */
@Service
public class ModeServiceImpl implements ModeService {

    @Autowired
    private ModeDao modeDao;

    /**
     * 获取赛道模式一级菜单
     * @return
     */
    @Override
    public ServerResponse modeTop() {
        try{
            return ServerResponse.getSuccess("获取成功", modeDao.modeTop());
        }catch (Exception e){
            e.printStackTrace();
            return ServerResponse.getError("获取失败");
        }
    }

    /**
     * 获取赛道模式二级菜单
     * @param modeId
     * @return
     */
    @Override
    public ServerResponse modeSecond(Integer modeId) {
        try{
            return ServerResponse.getSuccess("获取成功", modeDao.modeSecond(modeId));
        }catch (Exception e){
            e.printStackTrace();
            return ServerResponse.getError("获取失败");
        }
    }
}
