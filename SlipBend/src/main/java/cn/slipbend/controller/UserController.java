package cn.slipbend.controller;

import cn.slipbend.model.Area;
import cn.slipbend.model.User;
import cn.slipbend.service.UserService;
import cn.slipbend.util.ServerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: AnZX
 * @Date:
 * @Description:用户操作模块
 */
@RestController
@PropertySource(value = {"classpath:const.properties"})
@RequestMapping("/user")
@Api(value = "App接口", description = "操作用户的接口")
public class UserController {
    @Value("${IMAGES_PATH}")
    private String webRealPath;
    @Value("${LOCAL_IMAGES_PATH}")
    private String localRealPath;
    @Autowired
    private UserService userService;

    @Autowired
    private Area city;

    @RequestMapping("/updateUserInfo")
    @ApiOperation(value = "修改个人资料", httpMethod = "POST",notes = "修改个人资料")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header",name="token",dataType="String",required=true,value="token"),
            @ApiImplicitParam(paramType = "query",name = "id",value = "用户id",required = true,dataType = "Integer"),
            @ApiImplicitParam(paramType = "query",name = "imageUrl",value = "图片路径",required = false,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "username",value = "用户名",required = false,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "sex",value = "性别",required = false,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "age",value = "年龄",required = false,dataType = "Integer"),
            @ApiImplicitParam(paramType = "query",name = "hobby",value = "爱好",required = false,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "city",value = "城市num",required = false,dataType = "String"),
    })
    public Object updateUserInfo(Integer id,String imageUrl,String username,String sex,Integer age,String hobby,String cityNum){
        User user = new User();
        user.setId(id);
        user.setIcon(imageUrl);
        user.setUsername(username);
        user.setSex(sex);
        user.setAge(age);
        user.setHobby(hobby);
        city.setNum(cityNum);
        user.setCity(city);
        System.out.println(user);
        return userService.updateUserInfo(user);
    }

    @RequestMapping("/updateCarInfo")
    @ApiOperation(value = "修改车辆信息", httpMethod = "POST",notes = "修改车辆信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header",name="token",dataType="String",required=true,value="token"),
            @ApiImplicitParam(paramType = "query",name = "id",value = "用户id",required = true,dataType = "Integer"),
            @ApiImplicitParam(paramType = "query",name = "carModel",value = "汽车型号",required = false,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "carAge",value = "车龄",required = false,dataType = "Integer"),
            @ApiImplicitParam(paramType = "query",name = "engineModel",value = "引擎型号",required = false,dataType = "String"),
    })
    public Object updateCarInfo(Integer id,String carModel,Integer carAge,String engineModel){
        if(StringUtils.isBlank(carModel) && StringUtils.isBlank(carModel) && carAge == null){
            return null;
        }
        return userService.updateCarInfo(id,carModel,carAge,engineModel);
    }

    @RequestMapping("/drivingLevel")
    @ApiOperation(value = "我的-行驶等级", httpMethod = "POST",notes = "查看我的行驶等级")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header",name="token",dataType="String",required=true,value="token"),
            @ApiImplicitParam(paramType = "query",name = "id",value = "用户id",required = true,dataType = "Integer"),
    })
    public Object drivingLevel(Integer id){
        return userService.findUserDrivingLength(id);
    }

    @RequestMapping("/toLevelDate")
    @ApiOperation(value = "我的-行驶等级-到达给定公里数的日期", httpMethod = "POST",notes = "到达给定公里数的日期")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header",name="token",dataType="String",required=true,value="token"),
            @ApiImplicitParam(paramType = "query",name = "userId",value = "用户id",required = true,dataType = "Integer"),
            @ApiImplicitParam(paramType = "query",name = "mileage",value = "公里数(字符串数组)，示例：50,100,200",required = true,dataType = "String[]"),
    })
    public ServerResponse toLevelDate(Integer userId, String[] mileage){
        return userService.toLevelDate(userId, mileage);
    }

    @RequestMapping("/completePeople")
    @ApiOperation(value = "查看达成里程成就的人数", httpMethod = "POST",notes = "根据里程公里数查看达成里程成就的人数")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header",name="token",dataType="String",required=true,value="token"),
            @ApiImplicitParam(paramType = "query",name = "mileage",value = "里程公里数",required = true,dataType = "String"),
    })
    public Object completePeople(Double mileage){
        return userService.findCompletePeopleByMileage(mileage);
    }

    @RequestMapping("/userFollow")
    @ApiOperation(value = "查看我的关注列表", httpMethod = "POST",notes = "查看我的关注列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header",name="token",dataType="String",required=true,value="token"),
            @ApiImplicitParam(paramType = "query",name = "id",value = "用户id",required = true,dataType = "Integer"),
    })
    public Object userFollow(Integer id){
        return userService.findUserFollow(id);
    }

    @RequestMapping("/userFans")
    @ApiOperation(value = "查看我的粉丝列表", httpMethod = "POST",notes = "查看我的粉丝列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header",name="token",dataType="String",required=true,value="token"),
            @ApiImplicitParam(paramType = "query",name = "id",value = "用户id",required = true,dataType = "Integer"),
    })
    public Object userFans(Integer id){
        return userService.findUserFans(id);
    }

    @RequestMapping("/userInfo")
    @ApiOperation(value = "查看用户个人信息", httpMethod = "POST",notes = "查看用户个人信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header",name="token",dataType="String",required=true,value="token"),
            @ApiImplicitParam(paramType = "query",name = "id",value = "用户id",required = true,dataType = "Integer"),
            @ApiImplicitParam(paramType = "query",name = "isBaseInfo",value = "查询的是否为用户基础信息",required = true,dataType = "Boolean"),
    })
    public Object userInfo(Integer id, Boolean isBaseInfo){
        return userService.findUserInfo(id,isBaseInfo);
    }


    @RequestMapping("/updateSignInWith")
    @ApiOperation(value = "修改 是否允许第三方登录", httpMethod = "POST",notes = "修改 是否允许第三方登录")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header",name="token",dataType="String",required=true,value="token"),
            @ApiImplicitParam(paramType = "query",name = "userId",value = "用户id",required = true,dataType = "Integer"),
            @ApiImplicitParam(paramType = "query",name = "weChatStatus",value = "1:允许登录/0:不允许",required = false,dataType = "Integer"),
            @ApiImplicitParam(paramType = "query",name = "qqStatus",value = "1:允许登录/0:不允许",required = false,dataType = "Integer"),
    })
    public ServerResponse signInWith(Integer userId,Integer weChatStatus,Integer qqStatus){
        return userService.updateSignInWith(userId,weChatStatus,qqStatus);
    }

    @RequestMapping("/deleteAccount")
    @ApiOperation(value = "注销用户", httpMethod = "POST",notes = "注销用户")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header",name="token",dataType="String",required=true,value="token"),
            @ApiImplicitParam(paramType = "query",name = "userId",value = "用户id",required = true,dataType = "Integer"),
    })
    public ServerResponse deleteAccount(Integer userId){
        return userService.deleteAccount(userId);
    }

    @RequestMapping("/getSignInWith")
    @ApiOperation(value = "获取 第三方登录状态,1允许0不允许", httpMethod = "GET",notes = "获取 第三方登录状态,1允许0不允许")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header",name="token",dataType="String",required=true,value="token"),
            @ApiImplicitParam(paramType = "query",name = "userId",value = "用" +
                    "户id",required = true,dataType = "Integer"),
    })
    public ServerResponse getSignInWith(Integer userId){
        return userService.getSignInWith(userId);
    }

    @RequestMapping("/updateAttention")
    @ApiOperation(value = "修改 是否接收未关注对象的消息,1接收2不接收", httpMethod = "POST",notes = "修改 是否接收未关注对象的消息,1接收2不接收")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header",name="token",dataType="String",required=true,value="token"),
            @ApiImplicitParam(paramType = "query",name = "userId",value = "用户id",required = true,dataType = "Integer"),
            @ApiImplicitParam(paramType = "query",name = "noAttentionStatus",value = "1:接受/2:不接受",required =true,dataType = "Integer")
    })
    public ServerResponse updateAttention(Integer userId,Integer noAttentionStatus){
        return userService.updateAttention(userId,noAttentionStatus);
    }
    @RequestMapping("/getAttention")
    @ApiOperation(value = "获取 接收未关注对象的消息的状态,1接受2不接受", httpMethod = "GET",notes = "获取 接收未关注对象的消息的状态,1接受2不接受")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header",name="token",dataType="String",required=true,value="token"),
            @ApiImplicitParam(paramType = "query",name = "userId",value = "用" +
                    "户id",required = true,dataType = "Integer"),
    })
    public ServerResponse getAttention(Integer userId){
        return userService.getAttention(userId);
    }

    @RequestMapping("/updateNearby")
    @ApiOperation(value = "修改 是否出现附近", httpMethod = "POST",notes = "修改 是否出现附近")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header",name="token",dataType="String",required=true,value="token"),
            @ApiImplicitParam(paramType = "query",name = "userId",value = "用户id",required = true,dataType = "Integer"),
            @ApiImplicitParam(paramType = "query",name = "nearbyStatus",value = "1:允许/2:不允许",required = true,dataType = "Integer"),
    })
    public ServerResponse updateNearby(Integer userId, Integer nearbyStatus){
        return userService.updateNearby(userId,nearbyStatus);
    }
    @RequestMapping("/getSignNearby")
    @ApiOperation(value = "获取 是否出现附近,1允许0不允许", httpMethod = "GET",notes = "获取 是否出现附近,1允许0不允许")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header",name="token",dataType="String",required=true,value="token"),
            @ApiImplicitParam(paramType = "query",name = "userId",value = "用户id",required = true,dataType = "Integer"),
    })
    public ServerResponse getSignNearby(Integer userId) {
        return userService.getSignNearby(userId);
    }

    @RequestMapping("/updatebackgroundImage")
    @ApiOperation(value = "修改背景图片", httpMethod = "POST",notes = "修改背景图片")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header",name="token",dataType="String",required=true,value="token"),
            @ApiImplicitParam(paramType = "query",name = "userId",value = "用户id",required = true,dataType = "Integer"),
            @ApiImplicitParam(paramType = "query",name = "backgroudImage",value = "背景图片",required = true,dataType = "String"),

    })
    public ServerResponse updatebackgroundImage(Integer userId,String backgroudImage) {

        return userService.updatebackgroundImage(userId,backgroudImage);
    }
}


