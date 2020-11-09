package cn.slipbend.controller;

import cn.slipbend.service.RankService;
import cn.slipbend.util.DateUtil;
import cn.slipbend.util.ServerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author tht
 * @version 1.0
 */
@RestController
@RequestMapping("/rank")
@Api(value = "App接口", description = "排名表")
public class RankController {
    @Autowired
    private RankService rankService;

    @RequestMapping("/getFriendOrCityRank")
    @ApiOperation(value = "好友或地区排名", httpMethod = "GET", notes = "好友或地区排名")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header",name="token",dataType="String",required=true,value="token"),
            @ApiImplicitParam(paramType = "query", name = "id", value = "用户id", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "friendOrCity", value = "好友或城市(5好友6地区)", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "rankType", value = "排名类型(1行驶里程;2最高时速;3拥堵耗时;4油耗)", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "pageSize", value = "每页数据量", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "pageNumber", value = "第几页", required = true, dataType = "Integer"),
    })
    public ServerResponse getRank(String id, Integer rankType, Integer pageSize, Integer pageNumber,Integer friendOrCity) {
        if((rankType < 1 || rankType > 4) || (friendOrCity < 5 || friendOrCity > 6)){
            return null;
        }
        Integer a = (pageNumber - 1) * pageSize;
        Integer b = pageSize;

        Map<String, Object> param = new HashMap<>();
        param.put("id", id);
        param.put("friendOrCity",friendOrCity);
        param.put("rankType", rankType);
        param.put("dateOrWeek", 7);
        param.put("a", a);
        param.put("b", b);
        return rankService.getRank(param);
    }

    @RequestMapping("/getMyWeekRank")
    @ApiOperation(value = "我的本周排名和用户头像", httpMethod = "GET", notes = "我的本周排名和用户头像")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header",name="token",dataType="String",required=true,value="token"),
            @ApiImplicitParam(paramType = "query", name = "id", value = "用户id", required = true, dataType = "Integer"),
    })
    public ServerResponse rankIcon(String id) {

        Map<String, Object> param = new HashMap<>();
        param.put("id", id);
        param.put("dateOrWeek", 8);
        param.put("rankType",1);
        String array[]=DateUtil.getTimeInterval(new Date()).split(",");
        String startDate=array[0];//本周第一天
        String endDate=array[1];  //本周最后一天
        param.put("startDate",startDate);
        param.put("endDate",endDate);
        return rankService.rankIcon(param);
    }
}
