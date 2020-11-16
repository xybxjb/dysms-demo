package cn.slipbend.controller;

import cn.slipbend.service.NearService;
import cn.slipbend.util.ServerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 附近功能
 */
@RestController
@RequestMapping("/near")
@Api(value = "App接口", description = "附近功能接口")
public class NearController {

    @Autowired
    private NearService nearService;

    /**
     * 保存用户位置
     * @return
     */
    @RequestMapping("/saveUserPosition")
    @ApiOperation(value = "上传用户位置", httpMethod = "POST",notes = "上传用户位置")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header",name = "token",dataType = "String",required = true,value = "token"),
            @ApiImplicitParam(paramType = "query",name = "userId",value = "用户id",required = true,dataType = "Integer"),
            @ApiImplicitParam(paramType = "query",name = "longitude",value = "经度",required = true,dataType = "Double"),
            @ApiImplicitParam(paramType = "query",name = "latitude",value = "纬度",required = true,dataType = "Double"),
    })
    public ServerResponse save(Integer userId, Double longitude, Double latitude) {
        return nearService.saveUserPosition(userId,longitude,latitude);
    }

    /**
     * 删除用户位置
     * @return
     */
    @RequestMapping("/deleteUserPosition")
    @ApiOperation(value = "删除用户位置", httpMethod = "POST",notes = "删除用户位置")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header",name="token",dataType="String",required=true,value="token"),
            @ApiImplicitParam(paramType = "query",name = "userId",value = "用户id",required = true,dataType = "Integer")
    })
    public ServerResponse delete(Integer userId) {
        return nearService.deleteUserPosition(userId);
    }

    /**
     * 获取附近xkm的用户
     * @param longitude 经度
     * @param latitude 纬度
     * @param distance 距离
     * @param count 查询数量
     * @return
     */
    @RequestMapping("/nearByXY")
    @ApiOperation(value = "获取附近 x km 的用户", httpMethod = "POST",notes = "获取附近 x km 的用户")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header",name="token",dataType="String",required=true,value="token"),
            @ApiImplicitParam(paramType = "query",name = "longitude",value = "经度",required = true,dataType = "Double"),
            @ApiImplicitParam(paramType = "query",name = "latitude",value = "纬度",required = true,dataType = "Double"),
            @ApiImplicitParam(paramType = "query",name = "distance",value = "要查询的距离范围km，（传入示例：500）",required = true,dataType = "Integer"),
            @ApiImplicitParam(paramType = "query",name = "count",value = "要查询的数量",required = true,dataType = "Integer"),
    })
    public ServerResponse nearByXY(Double longitude, Double latitude, Integer distance, Integer count) {
        return nearService.nearByXY(longitude, latitude, distance, count);
    }

    @RequestMapping("/userInfo")
    @ApiOperation(value = "(附近)根据用户id查询出相应的用户信息", httpMethod = "POST",notes = "(附近)根据用户id查询出相应的用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header",name="token",dataType="String",required=true,value="token"),
            @ApiImplicitParam(paramType = "query",name = "id",value = "用户id",required = true,dataType = "Integer"),
    })
    public ServerResponse userInfo(Integer id) {
        return nearService.userInfo(id);
    }

    /**
     * 保存左滑的用户
     * @param userId 用户id
     * @param slideLeftUserId 被左滑的用户id
     * @return
     */
//    @RequestMapping("/slideToLeft")
//    @ApiOperation(value = "左滑用户", httpMethod = "POST",notes = "左滑用户")
//    @ApiImplicitParams({
//            @ApiImplicitParam(paramType="header",name="token",dataType="String",required=true,value="token"),
//            @ApiImplicitParam(paramType = "query",name = "userId",value = "用户id",required = true,dataType = "Integer"),
//            @ApiImplicitParam(paramType = "query",name = "slideLeftUserId",value = "被左滑用户id",required = true,dataType = "Integer"),
//    })
//    public ServerResponse slideToLeft(Integer userId, Integer slideLeftUserId) {
//        return nearService.slideToLeft(userId, slideLeftUserId);
//    }

    /**
     * 保存右滑的用户(右划为喜欢)
     * @param userId 用户id
     * @param slideRightUserId 被右滑的用户id
     * @return
     */
    @RequestMapping("/slideToRight")
    @ApiOperation(value = "右滑用户", httpMethod = "POST",notes = "右滑用户")
    @ApiImplicitParams(
            {@ApiImplicitParam(paramType="header",name="token",dataType="String",required=true,value="token"),
            @ApiImplicitParam(paramType = "query",name = "userId",value = "用户id",required = true,dataType = "Integer"),
            @ApiImplicitParam(paramType = "query",name = "slideRightUserId",value = "被右滑用户id",required = true,dataType = "Integer"),
    })
    public ServerResponse slideToRight(Integer userId, Integer slideRightUserId) {
        return nearService.slideToRight(userId, slideRightUserId);
    }

/*--------------------------------------------------------------------------------------------------------------------------------------------------------*/

    /**
     * 备注：此方法可以正常使用，只是用不到
     * 从 key 里返回所有给定位置元素的位置（经度和纬度）
     * @param userId 用户id
     * @return
     */
    @RequestMapping("/getUserPosition")
    @ApiOperation(value = "获取用户的位置", httpMethod = "POST",notes = "获取用户的位置")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header",name="token",dataType="String",required=true,value="token"),
            @ApiImplicitParam(paramType = "query",name = "userId",value = "用户id",required = true,dataType = "Integer")
    })
    public ServerResponse getUserPosition(Integer userId) {
        return nearService.getUserPosition(userId);
    }

    /**
     * 备注：此方法可以正常使用，只是用不到
     * 获取两个位置的距离
     * @return
     */
    @RequestMapping("/geoDist")
    @ApiOperation(value = "获取两个位置的距离", httpMethod = "POST",notes = "获取两个位置的距离")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header",name="token",dataType="String",required=true,value="token"),
            @ApiImplicitParam(paramType = "query",name = "userId",value = "当前用户id",required = true,dataType = "Integer"),
            @ApiImplicitParam(paramType = "query",name = "otherUserId",value = "另外一个用户的id",required = true,dataType = "Integer")
    })
    public ServerResponse geoDist(Integer userId, Integer otherUserId) {
        return nearService.geoDist(userId,otherUserId);
    }

}
