package cn.slipbend.service;

import cn.slipbend.util.ServerResponse;


public interface DrivingDataService {
    ServerResponse getMileageTimesOil(String id, String date);

    ServerResponse getRouteRecords(String id, String date);

    ServerResponse getToMileage(String id);

    ServerResponse getTmileage(String id, String date,Integer dateType) throws Exception;

}
