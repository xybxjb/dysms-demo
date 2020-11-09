package cn.slipbend.service.impl;

import cn.slipbend.dao.RankDao;
import cn.slipbend.service.RankService;
import cn.slipbend.util.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tht
 * @version 1.0
 */
@Service
public class RankServiceImpl implements RankService {
    @Autowired
    private RankDao rankDao;
    @Override
    public ServerResponse getRank(Map<String, Object> param) {
        String msg = null;
        String msg1 ="好友排名-";
        try {
            if (1==(Integer)param.get("rankType")) {
                msg = "获取行驶里程数据排名成功";
            }
            if (2==(Integer)param.get("rankType")) {
                msg = "获取最高时速数据排名成功";
            }
            if (3==(Integer)param.get("rankType")) {
                msg = "获取拥堵耗时数据排名成功";
            }
            if (4==(Integer)param.get("rankType")) {
                msg = "获取油耗数据排名成功";
            }
            if(6==(Integer)param.get("friendOrCity")){
                msg1 = "地区排名-";
                param.put("city",rankDao.getCityById(param.get("id")));
            }
            Map<String, Object> map = new HashMap<>();
            map.put("myRank", rankDao.getMyRank(param));
            map.put("friendOrCityRank", rankDao.getFriendOrCityRank(param));
            map.put("totalRank", rankDao.totalRank(param));
            return ServerResponse.getSuccess(msg1+msg, map);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.getError("获取数据失败");
        }
    }
    //自己本周排名
    @Override
    public ServerResponse rankIcon(Map<String, Object> param) {

        try {
            Map<String, Object> map = new HashMap<>();
            map.put("getFSIcon",rankDao.getFSIcon(param));
            map.put("getMyWeekRank",rankDao.getMyWeekRank(param));
            return ServerResponse.getSuccess("ok",map);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.getError("获取数据失败");
        }
    }

}
