package cn.slipbend.controller;

import cn.slipbend.service.DrivingDataService;
import cn.slipbend.util.ServerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/drivingData")
@Api(value = "App接口", description = "我的日月行驶数据")
public class DrivingDataController {
    @Autowired
    private DrivingDataService drivingDataService;

    @RequestMapping("/getMileageTimesOil")
    @ApiOperation(value = "获取某日或某月总里程-总油耗-完成次数", httpMethod = "GET",notes = "获取某日或某月总里程-总油耗-完成次数")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header",name="token",dataType="String",required=true,value="token"),
            @ApiImplicitParam(paramType = "query",name = "id",value = "用户id",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "date",value = "日期",required = true,dataType = "String"),
    })
    public ServerResponse getMileageTimesOil(String id, String date){
        return drivingDataService.getMileageTimesOil(id,date);
    }

    @RequestMapping("/routeRecords")
    @ApiOperation(value = "获取某日或某月总里程记录", httpMethod = "GET",notes = "获取某日或某月总里程记录")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header",name="token",dataType="String",required=true,value="token"),
            @ApiImplicitParam(paramType = "query",name = "id",value = "用户id",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "date",value = "日期",required = true,dataType = "String"),
     })
    public ServerResponse routeRecords(String id, String date){
        return drivingDataService.getRouteRecords(id,date);
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
    @ApiOperation(value = "获取五个月内或五天内总行驶里程记录", httpMethod = "GET",notes = "获取五个月内或五天内总行驶里程记录")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header",name="token",dataType="String",required=true,value="token"),
            @ApiImplicitParam(paramType = "query",name = "id",value = "用户id",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "date",value = "日期",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "dateType",value = "1:月/2:日",required = true,dataType = "Integer"),
    })
    public ServerResponse Tmileage(String id,String date,Integer dateType) throws Exception {
        if((dateType < 1 || dateType > 2)){
            return null;
        }
        return drivingDataService.getTmileage(id,date,dateType);

    }
}
