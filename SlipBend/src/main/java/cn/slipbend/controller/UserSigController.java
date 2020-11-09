package cn.slipbend.controller;

import cn.slipbend.service.UserSigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 即时通讯相关
 */
@RestController
@Api(value = "UserSig接口", description = "UserSig的接口")
@RequestMapping("/userSig")
public class UserSigController {

    @Autowired
    private UserSigService userSigService;

    @RequestMapping("/getUserSig")
    @ApiOperation(value = "获取 UserSig", httpMethod = "GET",notes = "获取 UserSig")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header",name="token",dataType="String",required=true,value="token"),
            @ApiImplicitParam(paramType = "query",name = "userId",value = "用户id",required = true,dataType = "Integer"),
    })
    public String getUserSig(String userId){
        return userSigService.generateUserSig(userId);
    }

}
