package cn.slipbend.service.impl;

import cn.slipbend.dao.DynamicDao;
import cn.slipbend.dao.RouteDao;
import cn.slipbend.model.Dynamic;
import cn.slipbend.service.DynamicService;
import cn.slipbend.util.RedisUtil;
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
public class DynamicServiceImpl implements DynamicService {
    @Autowired
    private DynamicDao dynamicDao;

    @Autowired
    private RouteDao routeDao;

    @Autowired
    private RedisUtil redisUtil;

    //发布新动态
    @Override
    public ServerResponse releaseNews(String id, String text, String imgsUrl, String power, double lon, double lat, String locationText) {
        try {
            dynamicDao.saveReleaseNews(id,text,imgsUrl,power,lon,lat,locationText);
            return ServerResponse.getSuccess("保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.getError("保存失败");
        }
    }
    //动态关注
    @Override
    public ServerResponse attentions(String id) {
        try {
            return ServerResponse.getSuccess("获取数据成功",dynamicDao.getAttentions(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.getError("获取数据失败");
        }
    }

    /**
     * 动态推荐
     * @param userId 用户id
     * @param pageNumber 第几页
     * @param pageSize 每页的数据量
     * @return
     */
    @Override
    public ServerResponse recommend(Integer userId, Integer pageNumber, Integer pageSize) {
        try {
            // 右滑喜欢的用户的动态
//            redisUtil.hget();


            // 附近的人的动态

            // 同城的人的动态

            // 动态表中按点赞高低排序的动态

            Map<String, Object> param = new HashMap<>();

            Integer a = (pageNumber - 1) * pageSize;
            Integer b = pageSize;
            param.put("userId", userId);
            param.put("a", a);
            param.put("b", b);
            return ServerResponse.getSuccess("获取数据成功",dynamicDao.getRecommend(param));
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.getError("获取数据失败");
        }
    }

    /**
     * 获取动态浏览量 或 动态浏览量累计
     * @param type 1获取该条动态浏览量；2累计浏览量
     * @param dynamicId 动态id
     * @return 动态浏览量
     */
    @Override
    public ServerResponse views(Integer type, Integer dynamicId) {
        String dynamicViews = "views" + dynamicId;
        // 浏览量
        Long views;

        switch (type){
            case 1:
                String viewsTemp = (String) redisUtil.getByStringRedisTemplate(dynamicViews);
                views = viewsTemp == null ? 0 : Long.valueOf(viewsTemp);
                break;
            case 2:
                views = redisUtil.increment(dynamicViews);
                break;
            default:
                return ServerResponse.getError("不符合条件的 type 传入");
        }
        return ServerResponse.getSuccess("该条动态的浏览量",views);
    }

    /**
     * 获取某条具体的动态 及 发布该动态的用户的竞技次数
     * @param dynamicId 动态id
     * @return
     */
    @Override
    public ServerResponse getDynamic(Integer dynamicId) {
        try {
            Dynamic dynamic = dynamicDao.getDynamic(dynamicId);
            Map<String,Object> map = new HashMap<>();
            map.put("dynamic",dynamic);
            map.put("competitionTimes",routeDao.countRouteRecord(dynamic.getUserId().getId()));

            return ServerResponse.getSuccess("获取动态成功",map);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.getError("获取动态失败");
        }
    }
    //查看我的所有动态
    @Override
    public ServerResponse getMyAllDynamic(Integer userId,Integer pageSize,Integer pageNumber) {
        try {
            Integer a = (pageNumber - 1) * pageSize;
            Integer b = pageSize;
            return ServerResponse.getSuccess("获取动态成功",dynamicDao.getMyAllDynamic(userId,a,b));
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.getError("获取动态失败");
        }
    }

}
