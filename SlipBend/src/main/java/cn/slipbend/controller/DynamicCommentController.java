package cn.slipbend.controller;

import cn.slipbend.model.Comment;
import cn.slipbend.model.Dynamic;
import cn.slipbend.model.User;
import cn.slipbend.service.DynamicCommentService;
import cn.slipbend.util.ServerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/dynamicComment")
@Api(value = "App接口", description = "动态评论")
public class DynamicCommentController {

    @Autowired
    private DynamicCommentService dynamicCommentService;

    @Autowired
    private Dynamic dynamic;

    @Autowired
    private User user;

    @RequestMapping("/getComment")
    @ApiOperation(value = "动态详情-获取动态的评论及2条子评论", httpMethod = "GET",notes = "动态详情-获取动态的评论及2条子评论")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header",name = "token",dataType = "String",required = true,value = "token"),
            @ApiImplicitParam(paramType = "query",name = "dynamicId",value = "动态id",required = true,dataType = "Integer"),
            @ApiImplicitParam(paramType = "query",name = "order",value = "排序条件(1按热度；2按时间)",required = true,dataType = "Integer"),
            @ApiImplicitParam(paramType = "query",name = "pageNumber",value = "第几页",required = true,dataType = "Integer"),
            @ApiImplicitParam(paramType = "query",name = "pageSize",value = "每页的数据量",required = true,dataType = "Integer"),
            @ApiImplicitParam(paramType = "query",name = "userId",value = "当前用户id",required = true,dataType = "Integer"),
    })
    public ServerResponse getDynamicComment(Integer dynamicId,Integer order,Integer pageNumber,Integer pageSize,Integer userId){
        Integer a = (pageNumber - 1) * pageSize;
        Integer b = pageSize;

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("dynamicId",dynamicId);
        param.put("order",order);
        param.put("a",a);
        param.put("b",b);
        param.put("userId",userId);
        return dynamicCommentService.getComment(param);
    }

    @RequestMapping("/countComment")
    @ApiOperation(value = "动态详情-获取动态评论的总数量", httpMethod = "GET",notes = "动态详情-获取动态评论的总数量")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header",name = "token",dataType = "String",required = true,value = "token"),
            @ApiImplicitParam(paramType = "query",name = "dynamicId",value = "动态id",required = true,dataType = "Integer"),
    })
    public ServerResponse countComment(Integer dynamicId){
        return dynamicCommentService.countComment(dynamicId);
    }

    @RequestMapping("/getCommentOfComment")
    @ApiOperation(value = "动态详情-获取评论的评论", httpMethod = "GET",notes = "动态详情-获取评论的评论")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header",name = "token",dataType = "String",required = true,value = "token"),
            @ApiImplicitParam(paramType = "query",name = "dynamicId",value = "动态id",required = true,dataType = "Integer"),
            @ApiImplicitParam(paramType = "query",name = "commentId",value = "评论id",required = true,dataType = "Integer"),
            @ApiImplicitParam(paramType = "query",name = "order",value = "排序条件(1按热度；2按时间)",required = true,dataType = "Integer"),
            @ApiImplicitParam(paramType = "query",name = "pageNumber",value = "第几页",required = true,dataType = "Integer"),
            @ApiImplicitParam(paramType = "query",name = "pageSize",value = "每页的数据量",required = true,dataType = "Integer"),
            @ApiImplicitParam(paramType = "query",name = "userId",value = "当前用户id",required = true,dataType = "Integer"),
    })
    public ServerResponse getCommentOfComment(Integer dynamicId, Integer commentId, Integer order, Integer pageNumber, Integer pageSize, Integer userId){
        Integer a = (pageNumber - 1) * pageSize;
        Integer b = pageSize;

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("dynamicId",dynamicId);
        param.put("commentId",commentId);
        param.put("order",order);
        param.put("a",a);
        param.put("b",b);
        param.put("userId",userId);
        return dynamicCommentService.getCommentOfComment(param);
    }

    @RequestMapping("/postComment")
    @ApiOperation(value = "动态详情-发布评论或子评论（返回该动态的总评论数）", httpMethod = "POST",notes = "动态详情-发布评论或子评论（返回该动态的总评论数）")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header",name = "token",dataType = "String",required = true,value = "token"),
            @ApiImplicitParam(paramType = "query",name = "dynamicId",value = "动态id",required = true,dataType = "Integer"),
            @ApiImplicitParam(paramType = "query",name = "commentId",value = "评论id",required = false,dataType = "Integer"),
            @ApiImplicitParam(paramType = "query",name = "userId",value = "用户id",required = true,dataType = "Integer"),
            @ApiImplicitParam(paramType = "query",name = "text",value = "发布评论内容",required = true,dataType = "String"),
    })
    public ServerResponse postComment(Integer dynamicId,Integer commentId,Integer userId, String text){
        dynamic.setId(dynamicId);
        user.setId(userId);

        Comment comment = new Comment();
        comment.setDynamic(dynamic);
        comment.setUser(user);
        comment.setText(text);
        if(commentId != null){
            comment.setId(commentId);
        }
        return dynamicCommentService.postComment(comment);
    }

    @RequestMapping("/hotOperations")
    @ApiOperation(value = "动态详情-增加评论热度 | 取消评论热度 | (返回该条评论的总热度)", httpMethod = "POST",notes = "动态详情-增加评论热度 | 取消评论热度 | (返回该条评论的总热度)")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header",name = "token",dataType = "String",required = true,value = "token"),
            @ApiImplicitParam(paramType = "query",name = "ops",value = "操作：1增加评论热度；2取消评论热度",required = true,dataType = "Integer"),
            @ApiImplicitParam(paramType = "query",name = "dynamicId",value = "动态id",required = true,dataType = "Integer"),
            @ApiImplicitParam(paramType = "query",name = "commentId",value = "评论id",required = true,dataType = "Integer"),
            @ApiImplicitParam(paramType = "query",name = "userId",value = "用户id",required = true,dataType = "Integer"),
    })
    public ServerResponse hotOperations(Integer ops, Integer dynamicId, Integer commentId, Integer userId){
        if(ops == 1 || ops == 2){
            return dynamicCommentService.hotOperations(ops, dynamicId, commentId, userId);
        }
        return ServerResponse.getError("不符合条件的 type 传入");
    }

        @RequestMapping("/countCommentHot")
    @ApiOperation(value = "动态详情-获取动态评论的总热度", httpMethod = "GET",notes = "动态详情-获取动态评论的总热度")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header",name = "token",dataType = "String",required = true,value = "token"),
            @ApiImplicitParam(paramType = "query",name = "dynamicId",value = "动态id",required = true,dataType = "Integer"),
    })
    public ServerResponse countCommentHot(Integer dynamicId){
        return dynamicCommentService.countCommentHot(dynamicId);
    }

}
