package cn.slipbend.controller;

import cn.slipbend.service.DynamicGoodService;
import cn.slipbend.util.ServerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
@RequestMapping("/dynamicGood")
@Api(value = "App接口", description = "动态点赞")
public class DynamicGoodController {

    @Autowired
    private DynamicGoodService dynamicGoodService;

    /**
     * 点赞动态 | 取消点赞动态 | 查看是否点赞某条动态
     * @param ops 操作：1点赞动态；2取消点赞动态；3查看是否点赞某条动态
     * @param dynamicId 动态id
     * @param userId 用户id
     * @return 当前用户与该条动态的关注关系、该条动态的总点赞数、该条动态的最近点赞的前三个用户的信息
     */
    @RequestMapping("/operations")
    @ApiOperation(value = "点赞动态 | 取消点赞动态 | 查看是否点赞某条动态", httpMethod = "GET",notes = "点赞动态 | 取消点赞动态 | 查看是否点赞某条动态")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header",name = "token",dataType = "String",required = true,value = "token"),
            @ApiImplicitParam(paramType = "query",name = "ops",value = "操作：1点赞动态；2取消点赞动态；3查看是否点赞某条动态",required = true,dataType = "Integer"),
            @ApiImplicitParam(paramType = "query",name = "dynamicId",value = "动态id",required = true,dataType = "Integer"),
            @ApiImplicitParam(paramType = "query",name = "userId",value = "用户id",required = true,dataType = "Integer"),
    })
    public ServerResponse operations(Integer ops, Integer dynamicId, Integer userId){
        if(ops > 0 && ops < 4){
            return dynamicGoodService.operations(ops, dynamicId, userId);
        }
        return ServerResponse.getError("不符合的 ops 传入");
    }

}
