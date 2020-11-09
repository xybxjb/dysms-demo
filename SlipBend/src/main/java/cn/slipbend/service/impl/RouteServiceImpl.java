package cn.slipbend.service.impl;

import cn.slipbend.dao.RouteDao;
import cn.slipbend.dao.RouteHotDao;
import cn.slipbend.model.Mode;
import cn.slipbend.model.RouteRecord;
import cn.slipbend.service.RouteService;

import cn.slipbend.util.DateUtil;

import cn.slipbend.util.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Date:
 * @Description: 用户行程记录
 */
@Service
public class RouteServiceImpl implements RouteService {
    @Autowired
    private RouteDao routeDao;

    @Autowired
    private RouteHotDao routeHotDao;

    //根据modeName查询mode
    @Override
    public Mode findModeIdByModelName(String modeName) {
        return routeDao.findModeIdByModelName(modeName);
    }

    //插入用户行程记录
    @Override
    public Object insertRouteRecord(RouteRecord routeRecord) {
        Map<String,Object> res = new HashMap<>();
        routeDao.insertRouteRecord(routeRecord);
        res.put("msg","保存成功");
        return res;
    }

    //查询用户此模式的排名和最好成绩
    @Override
    public Object findModeHotAndRank(Integer userId, String modeName) {
        Map<String,Object> res = new HashMap<>();
        //查询modeName查找mode
        Mode mode = routeDao.findModeIdByModelName(modeName);
        //根据modeId查找所有子mode
        List<Mode> modeList = routeDao.findModeListByPid(mode.getId());
        for (Mode modes : modeList) {
            //查询用户在此模式的排名
            Integer ranking = routeDao.findModeRanking(userId,modes.getId());
            //查询用户在此模式的最好成绩
            Time bestGrades = routeDao.findBestGrades(userId,modes.getId());
            List list = new ArrayList();//记录热度 排名 成绩
            list.add(modes.getHot());
            list.add(ranking);
            list.add(bestGrades);
            res.put(modes.getModeName(),list);
        }
        return res;
    }

    /**
     * 精选路线
     * 根据 modeId 查询两个月内热度最高的路线及用户信息及该条赛道的参加人数
     * @return
     */
    @Override
    public ServerResponse findFeaturedRoute() {
        try{
            List<RouteRecord> records = new ArrayList<>();
            // 获取赛道热度排序
            List<Mode> modes = routeDao.findModesByHot();
            // 获得本月和上月的参数
            Date t = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
            String thisMonth = df.format(t);
            int dayOfMonth = DateUtil.getDayOfMonth(thisMonth);
            thisMonth = thisMonth + "-" + dayOfMonth;
            String lastMonth = DateUtil.getLastDate(thisMonth, 0, -1, 0);

            for (Mode mode : modes) {
                RouteRecord record = routeDao.findEarliestUserAndRoute(mode.getId(), thisMonth, lastMonth);
                if(record != null){
                    records.add(record);
                }
            }
            return ServerResponse.getSuccess("获取精选路线成功",records);
        }catch (Exception e){
            e.printStackTrace();
            return ServerResponse.getError("获取精选路线失败");
        }
    }

    //查看路线详情
    @Override
    public Object findRouteDetailById(Integer routeId) {
        Map<String,Object> res = new LinkedHashMap<>();
        RouteRecord routeRecord = routeDao.findRouteDetailById(routeId);
        res.put("routeDetail",routeRecord);
        return res;
    }
    //修改心情
    @Override
    public Object updateMood(Integer routeId, Integer mood) {
        Map<String,Object> res = new LinkedHashMap<>();
        routeDao.updateMoodByRouteId(routeId,mood);
        res.put("msg","修改成功");
        return res;
    }
    //行程添加相册
    @Override
    public Object addPhoto(Integer routeId, String photo) {
        Map<String,Object> res = new LinkedHashMap<>();
        routeDao.updatePhoto(routeId,photo);
        res.put("msg","上传成功");
        res.put("photo",photo);
        return res;
    }

    /**
     * 根据用户id查看该用户全部行程或相应分类下的行程
     * @param param
     * @return
     */
    @Override
    public ServerResponse getRoutes(Map<String, Object> param) {
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("myRoute", routeDao.getMyRoute(param));
            return ServerResponse.getSuccess("获取数据成功", map);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.getError("获取数据失败");
        }
    }

    /**
     * 批量删除用户行程
     * @param routeIds
     * @return
     */
    @Override
    public ServerResponse deleteRoutes(Integer[] routeIds){
        try {
            routeDao.deleteRoutes(routeIds);
            return ServerResponse.getSuccess("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.getError("删除失败");
        }
    }

    /**
     * 增加行程热度 | 取消行程热度
     * @param ops 操作：1增加行程热度；2取消行程热度
     * @param routeId 行程id
     * @param userId 用户id
     * @return 该条行程的热度、当前用户是否为该条行程增添了热度
     */
    @Override
    public ServerResponse hotOperations(Integer ops, Integer routeId, Integer userId) {
        try{
            switch (ops){
                // 增加行程热度
                case 1:
                    if(!routeHotDao.hasMyHot(routeId, userId)){
                        routeHotDao.giveHot(routeId, userId);
                    }
                    break;
                // 取消行程热度
                case 2:
                    routeHotDao.cancelHot(routeId, userId);
                    break;
            }

            // 返回的数据
            Map<String, Object> dataBack = new HashMap<>();
            dataBack.put("numRouteHot", routeHotDao.countRouteHot(routeId));
            dataBack.put("hasMyHot", routeHotDao.hasMyHot(routeId, userId));

            return ServerResponse.getSuccess("操作成功", dataBack);
        }catch (Exception e){
            e.printStackTrace();
            return ServerResponse.getError("操作失败");
        }
    }
}

