package cn.slipbend.controller;

import cn.slipbend.service.DynamicService;
import cn.slipbend.util.ServerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tht
 * @version 1.0
 */
@RestController
@RequestMapping("/dynamic")
@Api(value = "App接口", description = "我的动态信息")
public class DynamicController {
    @Autowired
    private DynamicService dynamicService;

    @RequestMapping("/addReleaseNews")
    @ApiOperation(value = "发布新动态", httpMethod = "POST",notes = "发布新动态")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header",name="token",dataType="String",required=true,value="token"),
            @ApiImplicitParam(paramType = "query",name = "id",value = "用户id",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "text",value = "文本",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "imgsUrl",value = "图片",required = true,dataType = "String[]"),
            @ApiImplicitParam(paramType = "query",name = "power",value = "权限",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "lon",value = "经度",required = true,dataType = "Double"),
            @ApiImplicitParam(paramType = "query",name = "lat",value = "纬度",required = true,dataType = "Double"),
            @ApiImplicitParam(paramType = "query",name = "locationText",value = "位置文本",required = true,dataType = "String"),
    })
    public ServerResponse releaseNews(String id, String text,String[] imgsUrl,String power,double lon,double lat, String locationText){
        String imgs = null;
        if(imgsUrl.length > 0){
            StringBuffer stringBuffer = new StringBuffer();
            for(String imgUrl:imgsUrl){
                stringBuffer.append(imgUrl + ",");
            }
            stringBuffer.deleteCharAt(stringBuffer.length()-1);
            imgs = stringBuffer.toString();
        }

        return dynamicService.releaseNews(id,text,imgs,power,lon,lat,locationText);
    }

    @RequestMapping("/getAttention")
    @ApiOperation(value = "动态-关注", httpMethod = "POST",notes = "动态-关注")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header",name="token",dataType="String",required=true,value="token"),
            @ApiImplicitParam(paramType = "query",name = "id",value = "用户id",required = true,dataType = "String"),
    })
    public ServerResponse attentions(String id){
        return dynamicService.attentions(id);
    }

    @RequestMapping("/getRecommend")
    @ApiOperation(value = "动态-推荐", httpMethod = "POST",notes = "动态-推荐")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header",name="token",dataType = "String",required = true,value = "token"),
            @ApiImplicitParam(paramType = "query",name = "userId",value = "用户id",required = true,dataType = "Integer"),
            @ApiImplicitParam(paramType = "query",name = "pageNumber",value = "第几页",required = true,dataType = "Integer"),
            @ApiImplicitParam(paramType = "query",name = "pageSize",value = "每页的数据量",required = true,dataType = "Integer"),
    })
    public ServerResponse recommend(Integer userId, Integer pageNumber, Integer pageSize){
        return dynamicService.recommend(userId, pageNumber, pageSize);
    }

    @RequestMapping("/views")
    @ApiOperation(value = "动态-浏览量查看或累计", httpMethod = "POST",notes = "动态-浏览量查看或累计")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header",name="token",dataType="String",required=true,value="token"),
            @ApiImplicitParam(paramType = "query",name = "type",value = "1获取该条动态浏览量；2累计浏览量",required = true,dataType = "Integer"),
            @ApiImplicitParam(paramType = "query",name = "dynamicId",value = "动态id",required = true,dataType = "Integer"),
    })
    public ServerResponse views(Integer type, Integer dynamicId){
        return dynamicService.views(type,dynamicId);
    }

    @RequestMapping("/getDynamic")
    @ApiOperation(value = "动态详情-获取某条具体的动态及竞技次数", httpMethod = "GET",notes = "动态详情-获取某条具体的动态及竞技次数")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header",name="token",dataType="String",required=true,value="token"),
            @ApiImplicitParam(paramType = "query",name = "dynamicId",value = "动态id",required = true,dataType = "Integer"),
    })
    public ServerResponse getDynamic(Integer dynamicId){
        return dynamicService.getDynamic(dynamicId);
    }

    @RequestMapping("/getMyAllDynamic")
    @ApiOperation(value = "查看我的所有动态", httpMethod = "GET",notes = "查看我的所有动态")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header",name="token",dataType="String",required=true,value="token"),
            @ApiImplicitParam(paramType = "query",name = "userId",value = "用户id",required = true,dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "pageSize", value = "每页数据量", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "pageNumber", value = "第几页", required = true, dataType = "Integer"),
    })
    public ServerResponse getMyAllDynamic(Integer userId,Integer pageSize,Integer pageNumber){
        return dynamicService.getMyAllDynamic(userId,pageSize,pageNumber);
    }
}
