package cn.slipbend.service;

import cn.slipbend.util.ServerResponse;


import java.text.ParseException;

import java.util.Map;


public interface DrivingDataService {
    ServerResponse getMileageTimesOil(Integer ops,String id, String date);

    //获取某日或某周或某月总里程记录
    ServerResponse getRouteRecords(Map<String, Object> param);

    ServerResponse getToMileage(String id);

    ServerResponse getTmileage(Map<String, Object> param);

    ServerResponse getWeekTmileage(Map<String, Object> param) throws Exception;

}
