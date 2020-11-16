package cn.slipbend.controller;

import cn.slipbend.service.DrivingDataService;
import cn.slipbend.util.DateUtil;
import cn.slipbend.util.ServerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static cn.slipbend.util.DateUtil.createSimpleDateFormat;


@RestController
@RequestMapping("/drivingData")
@Api(value = "App接口", description = "我的日月行驶数据")
public class DrivingDataController {
    @Autowired
    private DrivingDataService drivingDataService;

    @RequestMapping("/getMileageTimesOil")
    @ApiOperation(value = "获取某日或某周或某月总里程-总油耗-完成次数", httpMethod = "GET",notes = "获取某日或某周或某月总里程-总油耗-完成次数")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header",name="token",dataType="String",required=true,value="token"),
            @ApiImplicitParam(paramType = "query",name = "ops",value = "操作:1日月 || 2 周",required = true,dataType = "Integer"),
            @ApiImplicitParam(paramType = "query",name = "id",value = "用户id",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "date",value = "日期:(日:2020-11-11;月:2020-11) | 2周(格式:2020-11-11)",required = true,dataType = "String"),
    })
    public ServerResponse getMileageTimesOil(Integer ops,String id, String date){
        if(1 == ops || 2 == ops){
            return drivingDataService.getMileageTimesOil(ops,id,date);
        }
        return ServerResponse.getError("不符合的 ops 传入");
    }

    @RequestMapping("/routeRecords")
    @ApiOperation(value = "获取某日或某周或某月的里程记录", httpMethod = "GET",notes = "获取某日或某周或某月的里程记录")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header",name="token",dataType="String",required=true,value="token"),
            @ApiImplicitParam(paramType = "query",name = "id",value = "用户id",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "date",value = "日期",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "dateOrWeek", value = "1日月;2周)", required = true, dataType = "Integer"),
     })
    public ServerResponse routeRecords(String id, String date,Integer dateOrWeek) throws Exception {
        Map<String, Object> param = new HashMap<>();
        if (1 == dateOrWeek){
            param.put("date",date);
        }
        if(2 == dateOrWeek){
            SimpleDateFormat sdf = createSimpleDateFormat(date);
            Date date1 = sdf.parse(date);
            String array[]= DateUtil.getTimeInterval(date1).split(",");
            String startDate=array[0];//本周第一天
            String endDate=array[1];  //本周最后一天

            param.put("startDate",startDate);
            param.put("endDate",endDate);
        }
        param.put("id", id);
        param.put("dateOrWeek",dateOrWeek);

        return drivingDataService.getRouteRecords(param);
    }
    @RequestMapping("/getToMileage")
    @ApiOperation(value = "获取总里程-总油耗-完成次数", httpMethod = "GET",notes = "获取总里程-总油耗-完成次数")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header",name="token",dataType="String",required=true,value="token"),
            @ApiImplicitParam(paramType = "query",name = "id",value = "用户id",required = true,dataType = "String"),
    })
    public ServerResponse getToMileage(String id) {
        return drivingDataService.getToMileage(id);
    }
    @RequestMapping("/Tmileage")
    @ApiOperation(value = "获取五个月内或五天或五周内总行驶里程记录", httpMethod = "GET",notes = "获取五个月内或五天或五周内总行驶里程记录")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header",name="token",dataType="String",required=true,value="token"),
            @ApiImplicitParam(paramType = "query",name = "id",value = "用户id",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "date",value = "日期",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "dateType",value = "1:月/2:日/3:周",required = true,dataType = "Integer"),
    })
    public ServerResponse Tmileage(String id,String date,Integer dateType) throws Exception {
        Map<String, Object> param = new HashMap<>();
        param.put("id",id);
        param.put("date",date);
        param.put("dateType",dateType);
        if (dateType == 1 || dateType == 2) {
            return drivingDataService.getTmileage(param);
        } else if (dateType == 3) {
            return drivingDataService.getWeekTmileage(param);
        }
        return ServerResponse.getError("不符合的 dateType 参数传入");
    }
}
