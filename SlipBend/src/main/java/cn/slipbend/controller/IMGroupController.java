package cn.slipbend.controller;

import cn.slipbend.service.IMGroupService;
import cn.slipbend.util.ServerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/group")
@Api(value = "App接口", description = "圈子管理的接口")
public class IMGroupController {

    @Autowired
    private IMGroupService imGroupService;

    @RequestMapping("/insertIMGroup")
    @ApiOperation(value = "添加用户圈子", httpMethod = "POST", notes = "添加用户圈子")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "token", dataType = "String", required = true, value = "token"),
            @ApiImplicitParam(paramType = "query", name = "userId", value = "用户id", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "groupId", value = "圈子id", required = true, dataType = "String"),
    })
    public ServerResponse insertIMGroup(Integer userId, String groupId){
        return imGroupService.insertIMGroup(userId,groupId);
    }
    @RequestMapping("/deleteIMGroup")
    @ApiOperation(value = "删除用户圈子", httpMethod = "POST", notes = "删除用户圈子")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "token", dataType = "String", required = true, value = "token"),
            @ApiImplicitParam(paramType = "query", name = "userId", value = "用户id", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "groupId", value = "圈子id", required = true, dataType = "String"),
    })
    public ServerResponse deleteIMGroup(Integer userId, String groupId){
        return imGroupService.deleteIMGroup(userId,groupId);
    }
    @RequestMapping("/selectIMGroup")
    @ApiOperation(value = "根据用户id推送圈子", httpMethod = "GET", notes = "根据用户id推送圈子")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header",name = "token",dataType = "String",required = true,value = "token"),
            @ApiImplicitParam(paramType = "query", name = "userId", value = "用户id", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "pageSize", value = "每页数据量", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "pageNumber", value = "第几页", required = true, dataType = "Integer"),
    })
    public ServerResponse selectIMGroup(Integer userId, Integer pageSize, Integer pageNumber) {

        Integer a = (pageNumber - 1) * pageSize;
        Integer b = pageSize;
        Map<String, Object> param = new HashMap<>();
        param.put("userId", userId);
        param.put("a", a);
        param.put("b", b);
        return imGroupService.selectIMGroup(param);
    }
    }