package cn.slipbend.controller;

import cn.slipbend.service.HotService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: AnZX
 * @Date: 2020/07/31/17:47
 * @Description:热度模块
 */
@RestController
@RequestMapping("/route")
@Api(value = "App接口", description = "热度管理的接口")
public class HotController {
    @Autowired
    private HotService hotService;
    @RequestMapping("/addModeHot")
    @ApiOperation(value = "增加赛道模式热度", httpMethod = "POST",notes = "增加赛道模式热度")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header",name="token",dataType="String",required=true,value="token"),
            @ApiImplicitParam(paramType = "query",name = "modeName",value = "模式名称",required = true,dataType = "String"),
    })
    public Object addModeHot(String modeName){
        return hotService.addModeHot(modeName);
    }

}
