package cn.slipbend.controller;

import cn.slipbend.service.AreaServeice;
import cn.slipbend.util.ServerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/area")
@Api(value = "API接口", description = "地区接口")
public class AreaController {

    @Autowired
    private AreaServeice areaServeice;


    /**
     * 获得三级行政区划(全国)
     * @return
     */
    @RequestMapping("getArea")
    @ApiOperation(value = "获得地区树", httpMethod = "GET", notes = "获得地区树")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header",name="token",dataType="String",required=true,value="token"),
    })
    public ServerResponse getArea(){
        return areaServeice.getArea();
    }

    @RequestMapping("getAreaTop")
    @ApiOperation(value = "获得一级地区", httpMethod = "GET", notes = "获得一级地区")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header",name="token",dataType="String",required=true,value="token"),
    })
    public ServerResponse getAreaTop(){
        return areaServeice.getAreaTop();
    }

    @RequestMapping("getSubArea")
    @ApiOperation(value = "根据id获取其下子地区", httpMethod = "GET", notes = "根据id获取其下子地区")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header",name="token",dataType="String",required=true,value="token"),
            @ApiImplicitParam(paramType = "query",name = "id",value = "地区id",required = true,dataType = "Integer"),
    })
    public ServerResponse getSubArea(Integer id){
        return areaServeice.getSubArea(id);
    }

}