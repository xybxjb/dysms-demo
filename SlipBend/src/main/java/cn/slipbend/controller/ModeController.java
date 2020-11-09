package cn.slipbend.controller;

import cn.slipbend.service.ModeService;
import cn.slipbend.util.ServerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mode")
@Api(value = "App接口", description = "赛道模式接口")
public class ModeController {

    @Autowired
    private ModeService modeService;

    @RequestMapping("/modeTop")
    @ApiOperation(value = "获取赛道模式一级菜单", httpMethod = "POST",notes = "获取赛道模式一级菜单")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header",name = "token",dataType = "String",required = true,value = "token"),
    })
    public ServerResponse modeTop(){
        return modeService.modeTop();
    }

    @RequestMapping("/modeSecond")
    @ApiOperation(value = "获取赛道模式二级菜单", httpMethod = "POST",notes = "获取赛道模式二级菜单")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header",name = "token",dataType = "String",required = true,value = "token"),
            @ApiImplicitParam(paramType = "query",name = "modeId",value = "赛道id ",required = true,dataType = "Integer"),
    })
    public ServerResponse modeSecond(Integer modeId){
        return modeService.modeSecond(modeId);
    }
}
