package cn.slipbend.service.impl;

import cn.slipbend.dao.DrivingDataDao;
import cn.slipbend.service.DrivingDataService;
import cn.slipbend.util.DateUtil;
import cn.slipbend.util.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DrivingDataServiceImpl implements DrivingDataService {
    @Autowired
    private DrivingDataDao drivingDataDao;
    @Override
    public ServerResponse getMileageTimesOil(String id, String date) {

        return ServerResponse.getSuccess("获取数据成功", drivingDataDao.getMileageTimesOil(id, date));
    }

    @Override
    public ServerResponse getRouteRecords(String id, String date) {
        try {
            return ServerResponse.getSuccess("获取数据成功", drivingDataDao.getRouteRecords(id, date));
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
    public ServerResponse getTmileage(String id,String date,Integer dateType) throws Exception {
            if (dateType == 1) {
            String endDate = DateUtil.getLastDate(date, 0, -4, 0);
                return ServerResponse.getSuccess("修改成功",drivingDataDao.getTmileage(id, date,endDate,dateType));
            } else if (dateType == 2) {
             String endDate = DateUtil.getLastDate(date, 0, 0, -4);
                return ServerResponse.getSuccess("修改成功",drivingDataDao.getTmileage(id, date,endDate,dateType));
            }
        return ServerResponse.getSuccess("修改失败");
    }
}
