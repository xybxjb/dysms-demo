package cn.slipbend.controller;

import cn.slipbend.model.User;
import cn.slipbend.service.LoginService;
import cn.slipbend.util.AppMD5Util;
import cn.slipbend.util.JWTUtil;
import cn.slipbend.util.SMSsend;
import cn.slipbend.util.ServerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: AnZX
 * @Date:
 * @Description:用户登录模块
 */
@RestController
//@PropertySource(value = {"classpath:const.properties"})
@RequestMapping("/login")
@Api(value = "App接口", description = "登录的接口")
public class LoginController {

    @Autowired
    private LoginService loginService;

    /*@RequestMapping("/sendSMS")
    public void sendSMS(String phone){
        SMSsend.send(phone);
    }*/
    /*@RequestMapping("/phone")
    public Object phone(String code){
        Map<String,Object> res = new HashMap<>();
        res.put("success",true);
        if(code.equals(SMSsend._code.toString())) {
            res.put("msg","正确");
        }else{
            res.put("msg","失败");
        }
        return res;
    }*/


    @RequestMapping("/verifyToken")
    @ApiOperation(value = "验证token", httpMethod = "POST",notes = "验证token")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header",name="token",dataType="String",required=true,value="token"),
    })
    public Object verifyToken(){
        return "验证通过";
    }

    @RequestMapping("/sendSMS")
    @ApiOperation(value = "发送验证码", httpMethod = "POST",notes = "通过手机号发送验证码")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "phone",value = "手机号",required = true,dataType = "String"),
    })
    public Object sendSMS(String phone){
        SMSsend.send(phone);
        Map<String,Object> res = new HashMap<>();
        res.put("smsCode",SMSsend._code.toString());
        return res;
    }

    @RequestMapping("/loginByPassword")
    @ApiOperation(value = "密码登录", httpMethod = "POST",notes = "通过手机号和密码登录")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "phone",value = "手机号",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "password",value = "密码",required = true,dataType = "String")
    })
    public ServerResponse loginByPassword(String phone, String password){
        if(StringUtils.isBlank(phone)){
            return ServerResponse.getError("未填入手机号");
        }
        if(StringUtils.isBlank(password)){
            return ServerResponse.getError("登录失败,您未输入密码（如未设置过密码，设置后可使用密码登录）");
        }
        password = AppMD5Util.getMD52(password);
        User user = loginService.loginByPassword(phone,password);
        Map<String,Object> map = new HashMap<>();
        if(user!=null){
            map.put("token",JWTUtil.getToken(user));
            map.put("userId",user.getId());
            return ServerResponse.getSuccess("登录成功",map);
        }else {
            return ServerResponse.getError("登录失败,用户名或密码错误");
        }
    }

    @RequestMapping("/loginBySmscode")
    @ApiOperation(value = "验证码登录", httpMethod = "POST",notes = "通过手机号和验证码登录")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "phone",value = "手机号",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "smsCode",value = "短信验证码",required = true,dataType = "String")
    })
    public ServerResponse smsLogin(String phone,String smsCode){
        System.out.println("验证码登陆： 手机号" + phone + "  验证码" + smsCode);
        Map<String,Object> map = new HashMap<>();
        map.put("isNewUser",false);
        if(StringUtils.equals(smsCode,SMSsend._code.toString())){
            //查看手机号是否注册 没有则自动注册
            User user = loginService.findUserByPhone(phone);
            if(user==null){
                loginService.insertUser(phone);
                user = loginService.findUserByPhone(phone);
                map.put("isNewUser",true);
            }
            map.put("userId",user.getId());
            map.put("token",JWTUtil.getToken(user));
            return ServerResponse.getSuccess("登录成功",map);
        }else {
            return ServerResponse.getError("登录失败,用户名或密码错误");
        }
    }

    /**
     * 通过 微信 或 QQ 的 openId 实现用户登录或注册
     *
     * 情况1：若用户 未使用 手机号注册过此APP，使用 微信 或 QQ 作为 首次登录，则为该用户创建一个此APP账号，根据 微信 或 QQ 的 openId 查询此用户，返回 token 及 userId
     * 情况2：若用户 已使用 手机号注册过此APP，使用 微信 或 QQ 作为 首次登录，则将 微信 的 openId 保存到该用户下，根据 微信 或 QQ 的 openId 查询此用户，返回 token 及 userId
     * 情况3：若用户 已使用 手机号注册过此APP，使用 微信 或 QQ 作为 非首次登录，则直接根据 微信 或 QQ 的 openId 查询此用户，返回 token 及 userId
     *
     * @param openId 微信 或 QQ 的 openId
     * @param phone 手机号
     * @return
     */
    @RequestMapping("/loginByWeChatOrQQ")
    @ApiOperation(value = "通过微信或QQ登录", httpMethod = "POST",notes = "通过微信或QQ登录")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "openIdType",value = "openId类型(1微信；2QQ)",required = true,dataType = "Integer"),
            @ApiImplicitParam(paramType = "query",name = "openId",value = "openId",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "username",value = "昵称",required = false,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "icon",value = "头像",required = false,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "phone",value = "手机号",required = false,dataType = "String"),
    })
    public ServerResponse loginByWeChat(Integer openIdType, String openId,String username,String icon,String phone){
        String msg;
        switch (openIdType){
            case 1:
                msg = "微信";
                break;
            case 2:
                msg = "QQ";
                break;
            default:
                return ServerResponse.getError("传入openId类型有误");
        }

        openId = openId.replace("\"","");
        System.out.println(msg + "的openId:" + openId);

        Map<String,Object> map = new HashMap<>();
        // 标记用户是否是新用户
        map.put("isNewUser",false);

        User user = loginService.findUserByWeChatOrQQ(openIdType, openId);

        // 未使用微信注册
        if(user == null){

            // 若电话号码为空，则
            if(StringUtils.isBlank(phone)){
                map.put("isNewUser",true);
                return ServerResponse.getSuccess("新登入" + msg + "用户",map);
            }

            // 查看该微信用户是否已使用手机号进行注册过
            User userByPhone = loginService.findUserByPhone(phone);
            // 如果未使用手机号进行注册过，则为其开辟一个账号
            if(userByPhone == null){
                if(StringUtils.isNotBlank(username) && StringUtils.isNotBlank(icon)){
                    loginService.insertUserByWeChatOrQQ(openIdType,phone,openId,username,icon);
                }
                else{
                    return ServerResponse.getError("未传入昵称或头像");
                }
            }
            // 否则将此 openId 存入已有的手机号的账号下
            else{
                loginService.uptUserWeChatOrQQOpenId(openIdType,phone,openId);
            }
            user = loginService.findUserByWeChatOrQQ(openIdType,openId);
        }

        if(1 == openIdType){
            if(0 == user.getIsYWeChat()){
                return ServerResponse.getError("您已禁用微信登录");
            }
        }
        else{
            if(0 == user.getIsYQQ()){
                return ServerResponse.getError("您已禁用QQ登录");
            }
        }

        map.put("userId",user.getId());
        map.put("token",JWTUtil.getToken(user));
        return ServerResponse.getSuccess("登录成功",map);
    }

    @RequestMapping("/updatePassword")
    @ApiOperation(value = "重置密码", httpMethod = "POST",notes = "修改密码")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "phone",value = "手机号",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "newPassword",value = "新密码",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "newPassword2",value = "再次确认密码",required = true,dataType = "String")
    })
    public Object updatePassword(String phone,String newPassword,String newPassword2){
        Map<String,Object> res = new HashMap<>();
        if(StringUtils.equals(newPassword,newPassword2)){
            //判断是否注册
            User user = loginService.findUserByPhone(phone);
            if(user!=null){
                newPassword = AppMD5Util.getMD52(newPassword);
                loginService.updatePassword(phone,newPassword);
                res.put("msg","修改成功");
                return res;
            }else{
                res.put("msg","请先注册");
                return res;
            }
        }else{
            res.put("msg","修改失败,两次密码不一致");
            return res;
        }
    }

    @RequestMapping("/updateImageAndUsername")
    @ApiOperation(value = "完善头像和用戶名", httpMethod = "POST",notes = "完善头像和用戶名")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header",name="token",dataType="String",required=true,value="token"),
            @ApiImplicitParam(paramType = "query",name = "id",value = "用户id",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "imageUrl",value = "图片路径",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "username",value = "用户昵称",required = true,dataType = "String"),
    })
    public ServerResponse updateImageAndUsername(String id,String imageUrl,String username){
        if(StringUtils.isBlank(id) && StringUtils.isBlank(imageUrl) && StringUtils.isBlank(username)){
            return ServerResponse.getError("未传入id 或 用户昵称 或 图片路径");
        }
        return loginService.updateImageAndUsername(id,imageUrl,username);
    }

    @RequestMapping("/updateSexAndAge")
    @ApiOperation(value = "完善性别年龄", httpMethod = "POST",notes = "完善性别年龄")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header",name="token",dataType="String",required=true,value="token"),
            @ApiImplicitParam(paramType = "query",name = "id",value = "用户id",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "sex",value = "性别",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "age",value = "年龄",required = true,dataType = "Integer"),
    })
    public ServerResponse updateSexAndAge(String id,String sex,Integer age){
        if(StringUtils.isBlank(id) && StringUtils.isBlank(sex) && age == null){
            return ServerResponse.getError("未传入id 或 性别 或 年龄");
        }
        return loginService.updateSexAndAge(id,sex,age);
    }

    @RequestMapping("/updateCarModel")
    @ApiOperation(value = "完善汽车和发动机型号", httpMethod = "POST",notes = "完善汽车和发动机型号")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header",name="token",dataType="String",required=true,value="token"),
            @ApiImplicitParam(paramType = "query",name = "id",value = "用户id",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "carModel",value = "汽车型号",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "engineModel",value = "发动机型号",required = true,dataType = "String"),
    })
    public Object updateCarModel(String id,String carModel,String engineModel){
        return loginService.updateCarModel(id,carModel,engineModel);
    }


}
