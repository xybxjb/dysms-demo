package cn.slipbend.controller;

import cn.slipbend.model.Mode;
import cn.slipbend.model.RouteRecord;
import cn.slipbend.model.User;
import cn.slipbend.service.RouteService;
import cn.slipbend.util.ServerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: AnZX
 * @Date: 2020/07/31/14:29
 * @Description:用户行程路线模块
 */
@RestController
@RequestMapping("/route")
@Api(value = "App接口", description = "用户行程路线的接口")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @RequestMapping("/saveRoute")
    @ApiOperation(value = "保存行程", httpMethod = "POST",notes = "保存本次行程")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header",name="token",dataType="String",required=true,value="token"),
            @ApiImplicitParam(paramType = "query",name = "userId",value = "用户id",required = true,dataType = "Integer"),
            @ApiImplicitParam(paramType = "query",name = "modeId",value = "模式id",required = true,dataType = "Integer"),
            @ApiImplicitParam(paramType = "query",name = "sLongitude",value = "开始经度",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "sLatitude",value = "开始纬度",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "eLongitude",value = "结束经度",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "eLatitude",value = "结束纬度",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "time",value = "用时",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "avgSpeed",value = "平均速度",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "speed",value = "最高速度",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "leng",value = "路长(里程)",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "altitude",value = "海拔",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "marks",value = "点标记",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "imageUrl",value = "图片路径",required = false,dataType = "String"),
    })
    public Object saveRoute(Integer userId, Integer modeId, Double sLongitude, Double sLatitude, Double eLongitude, Double eLatitude, String time, Double avgSpeed, Double speed, Double leng, Double altitude,String marks,String imageUrl){
        RouteRecord routeRecord = new RouteRecord();
        User user = new User();
        user.setId(userId);
        routeRecord.setUser(user);
        routeRecord.setSLongitude(sLongitude);
        routeRecord.setSLatitude(sLatitude);
        routeRecord.setELongitude(eLongitude);
        routeRecord.setELatitude(eLatitude);
        routeRecord.setTime(time);
        routeRecord.setAvgSpeed(avgSpeed);
        routeRecord.setSpeed(speed);
        routeRecord.setLeng(leng);
        routeRecord.setAltitude(altitude);
        routeRecord.setImageUrl(imageUrl);
        //获取modeId
        Mode mode = new Mode();
        mode.setId(modeId);
        routeRecord.setMode(mode);

        routeRecord.setMarks(marks);
        return routeService.insertRouteRecord(routeRecord);
    }

    @RequestMapping("/featuredRoute")
    @ApiOperation(value = "精选路线", httpMethod = "POST",notes = "精选路线")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header",name="token",dataType="String",required=true,value="token"),
    })
    public ServerResponse featuredRoute(){
        return routeService.findFeaturedRoute();
    }

    @RequestMapping("/routeDetail")
    @ApiOperation(value = "查看行程详情", httpMethod = "POST",notes = "查看行程详情")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header",name="token",dataType="String",required=true,value="token"),
            @ApiImplicitParam(paramType = "query",name = "routeId",value = "路线行程id",required = true,dataType = "Integer"),
    })
    public Object routeDetail(Integer routeId){
        return routeService.findRouteDetailById(routeId);
    }

    @RequestMapping("/updateMood")
    @ApiOperation(value = "修改心情", httpMethod = "POST",notes = "修改心情")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header",name="token",dataType="String",required=true,value="token"),
            @ApiImplicitParam(paramType = "query",name = "routeId",value = "路线行程id",required = true,dataType = "Integer"),
            @ApiImplicitParam(paramType = "query",name = "mood",value = "心情",required = true,dataType = "Integer"),
    })
    public Object updateMood(Integer routeId,Integer mood){
        return routeService.updateMood(routeId,mood);
    }

    @RequestMapping("/addPhoto")
    @ApiOperation(value = "添加相册", httpMethod = "POST",notes = "添加相册")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header",name="token",dataType="String",required=true,value="token"),
            @ApiImplicitParam(paramType = "query",name = "routeId",value = "路线行程id",required = true,dataType = "Integer"),
            @ApiImplicitParam(paramType = "query",name = "photo",value = "相片",required = true,dataType = "String"),
    })
    public Object addPhoto(Integer routeId,String photo){

        return routeService.addPhoto(routeId,photo);
    }

    @RequestMapping("/getRoutes")
    @ApiOperation(value = "根据用户id查看该用户全部行程或相应分类下的行程", httpMethod = "GET", notes = "根据用户id查看该用户全部行程或相应分类下的行程")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header",name = "token",dataType = "String",required = true,value = "token"),
            @ApiImplicitParam(paramType = "query", name = "id", value = "用户id", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "modeId", value = "模式id", required = false, dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "pageSize", value = "每页数据量", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "pageNumber", value = "第几页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "loginUserId", value = "登陆用户id", required = true, dataType = "Integer"),
    })
    public ServerResponse getRoutes(String id,Integer modeId, Integer pageSize, Integer pageNumber, Integer loginUserId) {

        Integer a = (pageNumber - 1) * pageSize;
        Integer b = pageSize;
        Map<String, Object> param = new HashMap<>();
        param.put("id", id);
        param.put("modeId",modeId);
        param.put("a", a);
        param.put("b", b);
        param.put("loginUserId", loginUserId);
        return routeService.getRoutes(param);
    }

    /**
     * 删除行程（一条或多条）
     * @param routeIds
     * @return
     */
    @RequestMapping("/deleteRoutes")
    @ApiOperation(value = "删除行程（一条或多条）", httpMethod = "POST", notes = "删除行程（一条或多条）")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(paramType = "header", name = "token", dataType = "String", required = true, value = "token"),
            @ApiImplicitParam(paramType = "query", name = "routeIds", value = "行程ids", required = true, dataType = "Integer[]"),
    })
    public Object deleteRoutes(Integer[] routeIds) {
          return routeService.deleteRoutes( routeIds);
    }

    @RequestMapping("/hotOperations")
    @ApiOperation(value = "增加行程热度 | 取消行程热度", httpMethod = "POST", notes = "增加行程热度 | 取消行程热度")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(paramType = "header", name = "token", dataType = "String", required = true, value = "token"),
            @ApiImplicitParam(paramType = "query", name = "ops", value = "操作：1增加行程热度；2取消行程热度", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "routeId", value = "行程id", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "userId", value = "用户id", required = true, dataType = "Integer"),
    })
    public ServerResponse hotOperations(Integer ops, Integer routeId, Integer userId){
        if(ops > 0 && ops < 3){
            return routeService.hotOperations(ops, routeId, userId);
        }
        return ServerResponse.getError("不符合的 ops 传入");
    }

    @RequestMapping("/getPhoto")
    @ApiOperation(value = "获取行程照片",httpMethod = "GET",notes = "获取行程照片")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(paramType = "header", name = "token", dataType = "String", required = true, value = "token"),
            @ApiImplicitParam(paramType = "query",name = "userId",value = "用戶id",required = true,dataType = "Integer"),
            @ApiImplicitParam(paramType = "query",name = "pageNumber",value = "第几页",required = true,dataType = "Integer"),
            @ApiImplicitParam(paramType = "query",name = "pageSize",value = "每页的数据量",required = true,dataType = "Integer"),
    })
    public ServerResponse getPhoto(Integer userId,Integer pageNumber,Integer pageSize){
        Integer a = (pageNumber - 1) * pageSize;
        Integer b = pageSize;
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("a",a);
        param.put("b",b);
        param.put("userId",userId);
        return routeService.getPhoto(param);
    }

}
