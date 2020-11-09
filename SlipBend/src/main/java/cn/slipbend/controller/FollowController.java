package cn.slipbend.controller;

import cn.slipbend.service.FollowService;
import cn.slipbend.util.ServerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/follow")
@Api(value = "App接口", description = "关注接口")
public class FollowController {

    @Autowired
    private FollowService followService;

    /**
     * 添加关注 | 取消关注 | 查看当前用户是否关注了某个用户
     * @param userId
     * @param targetUserId
     * @return 查看当前用户是否关注了某个用户
     */
    @RequestMapping("/operations")
    @ApiOperation(value = "加关注 | 取消关注 | 查看当前用户是否关注了某个用户", httpMethod = "POST",notes = "加关注 | 取消关注 | 查看当前用户是否关注了某个用户")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header",name="token",dataType="String",required=true,value="token"),
            @ApiImplicitParam(paramType = "query",name = "ops",value = "1加关注；2取消关注；3查看是否关注",required = true,dataType = "Integer"),
            @ApiImplicitParam(paramType = "query",name = "userId",value = "用户id",required = true,dataType = "Integer"),
            @ApiImplicitParam(paramType = "query",name = "targetUserId",value = "目标用户id",required = true,dataType = "Integer"),
    })
    public ServerResponse follow(Integer ops, Integer userId, Integer targetUserId){
        if( 1 == ops || 2 == ops || 3 == ops){
            return followService.follow(ops, userId, targetUserId);
        }
        return ServerResponse.getError("不符合条件的 ops 传入");
    }

}
