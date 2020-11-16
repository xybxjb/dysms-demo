package cn.slipbend.service.impl;

import cn.slipbend.dao.DrivingDataDao;
import cn.slipbend.service.DrivingDataService;
import cn.slipbend.util.DateUtil;
import cn.slipbend.util.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.util.*;

import static cn.slipbend.util.DateUtil.createSimpleDateFormat;


@Service
public class DrivingDataServiceImpl implements DrivingDataService {
    @Autowired
    private DrivingDataDao drivingDataDao;

    private List<Map<String, Object>> fiveWeekTmileage = new ArrayList<>();
    private Integer count = 0;

    @Override
    public ServerResponse getMileageTimesOil(Integer ops,String id, String date) {
        Map<String, Object> param = new HashMap<>();
        param.put("id", id);
        param.put("ops", ops);
        //获取某日或某月总里程-总油耗-完成次数
        if(1 == ops){
            param.put("date", date);
        }
        //获取某周总里程-总油耗-完成次数
        if(2 == ops){
            String array[]= DateUtil.getTimeInterval(new Date()).split(",");
            String startDate=array[0];//本周第一天
            String endDate=array[1];  //本周最后一天
            param.put("startDate",startDate);
            param.put("endDate",endDate);
        }
        return ServerResponse.getSuccess("获取数据成功", drivingDataDao.getMileageTimesOil(param));
    }

    /**
     * 获取某日或某周或某月总里程记录
     *
     * @param param
     * @return
     */
    @Override
    public ServerResponse getRouteRecords(Map<String, Object> param) {
        try {
            return ServerResponse.getSuccess("获取数据成功", drivingDataDao.getRouteRecords(param));
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.getError("获取失败");
        }
    }

    @Override
    public ServerResponse getToMileage(String id) {

        return ServerResponse.getSuccess("获取数据成功", drivingDataDao.getToMileage(id));
    }

    @Override
    public ServerResponse getWeekTmileage(Map<String, Object> param) throws Exception {
        String date = (String) param.get("date");
        if(count == 5){
            return null;
        }

        SimpleDateFormat sdf = createSimpleDateFormat(date);
        Date date1 = sdf.parse(date);
        String array[] = DateUtil.getTimeInterval(date1).split(",");
        String startDate = array[0];//本周第一天
        String endDate = array[1];  //本周最后一天
        param.put("startDate",startDate);
        startDate = DateUtil.getLastDate(startDate, 0, 0, -1);
        param.put("endDate",endDate);
        param.put("date",startDate);
        Map<String, Object> weekTmileage = drivingDataDao.getWeekTmileage(param);
        if(!weekTmileage.containsKey("leng")){
            weekTmileage.put("leng",0);
        }
        fiveWeekTmileage.add(weekTmileage);
        count++;
        getWeekTmileage(param);
        return ServerResponse.getSuccess("获取五周行驶里程数成功", this.fiveWeekTmileage);
    }

    @Override
    public ServerResponse getTmileage(Map<String, Object> param) {
        try {
            String endDate = null;
            String date = (String) param.get("date");
            if ("1".equals(param.get("dateType"))) {
                endDate = DateUtil.getLastDate(date, 0, -4, 0);

            } else if ("2".equals(param.get("dateType"))) {
                endDate = DateUtil.getLastDate(date, 0, 0, -4);
            }
            param.put("endDate",endDate);
            return ServerResponse.getSuccess("获取成功", drivingDataDao.getTmileage(param));
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.getError("获取失败");
        }
    }
}
