package cn.slipbend.service.impl;

import cn.slipbend.dao.AreaDao;
import cn.slipbend.model.Area;
import cn.slipbend.service.AreaServeice;
import cn.slipbend.util.RedisUtil;
import cn.slipbend.util.ServerResponse;
import com.alibaba.fastjson.JSON;
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
            boolean result = redisUtil.hasKey(AREA_TREE);
            String areaTreeJson;

            if(result){
                areaTreeJson = (String) redisUtil.get(AREA_TREE);
            }else{
                // 地区树构建
                List<Area> listArea = areaDao.getArea();
                areaTreeJson = buildAreaTree(listArea);
                redisUtil.set(AREA_TREE,areaTreeJson);
            }
            return ServerResponse.getSuccess("获取地区树成功",areaTreeJson);
        }catch (Exception e){
            e.printStackTrace();
            return ServerResponse.getError("获取地区树失败");
        }
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

    private static String buildAreaTree(List<Area> listArea){
        String areaTreeJson = JSON.toJSONString(listArea);
//        areaTreeJson = areaTreeJson.replace("name","text");
//        areaTreeJson = areaTreeJson.replace("subArea","nodes");
        areaTreeJson = areaTreeJson.replace(",\"subArea\":[]","");
        return areaTreeJson;
    }
}
