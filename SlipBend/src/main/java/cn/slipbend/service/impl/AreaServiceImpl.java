package cn.slipbend.service.impl;

import cn.slipbend.dao.AreaDao;
import cn.slipbend.model.Area;
import cn.slipbend.service.AreaServeice;
import cn.slipbend.util.RedisUtil;
import cn.slipbend.util.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaServiceImpl implements AreaServeice {

    private final String AREA_TREE = "SLBAreaTree";

    @Autowired
    private AreaDao areaDao;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public Area getByName(String name) {
        return areaDao.getByName(name);
    }

    @Override
    public Area getByNum(String num) {
        return areaDao.getByNum(num);
    }

    @Override
    public ServerResponse getArea() {
        try{
            boolean hasAreaTree = redisUtil.hasKey(AREA_TREE);
            List<Area> listArea;

            if(hasAreaTree){
                listArea = (List<Area>) redisUtil.get(AREA_TREE);
            }else{
                // 地区树构建
                listArea = areaDao.getArea();
                redisUtil.set(AREA_TREE,listArea);
            }
            return ServerResponse.getSuccess("获取地区树成功",listArea);
        }catch (Exception e){
            e.printStackTrace();
            return ServerResponse.getError("获取地区树失败");
        }
    }

    /**
     * 删除缓存在 redis 的地区树
     * @return
     */
    @Override
    public ServerResponse delArea() {
        redisUtil.del(AREA_TREE);
        return ServerResponse.getSuccess("删除成功");
    }

    @Override
    public ServerResponse getAreaTop() {
        try {
            return ServerResponse.getSuccess("获取一级地区成功",areaDao.listAreaTop());
        }catch (Exception e){
            e.printStackTrace();
            return ServerResponse.getError("获取一级地区失败");
        }
    }

    @Override
    public ServerResponse getSubArea(Integer id) {
        try {
            return ServerResponse.getSuccess("获取子地区成功",areaDao.getSubArea(id));
        }catch (Exception e){
            e.printStackTrace();
            return ServerResponse.getError("获取子地区失败");
        }
    }

}
