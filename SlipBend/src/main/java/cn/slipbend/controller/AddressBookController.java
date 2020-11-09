package cn.slipbend.controller;

import cn.slipbend.service.AddressBookService;
import cn.slipbend.util.ServerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 通讯录相关
 */
@RestController
@RequestMapping("/addressBook")
@Api(value = "通讯录接口", description = "通讯录接口")
public class AddressBookController {
    @Autowired
    private final AddressBookService addressBookService;

    public AddressBookController(AddressBookService addressBookService) {
        this.addressBookService = addressBookService;
    }

    @RequestMapping("/uptAllow")
    @ApiOperation(value = "修改 是否允许通过通讯录找到我", httpMethod = "POST",notes = "修改 是否允许通过通讯录找到我")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header",name="token",dataType="String",required=true,value="token"),
            @ApiImplicitParam(paramType = "query",name = "id",value = "用户id",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "allowAddressBook",value = "1允许；2不允许",required = true,dataType = "Integer"),
    })
    public ServerResponse uptAllowAddressBook(String id, Integer allowAddressBook){
        return addressBookService.uptAllowAddressBook(id,allowAddressBook);
    }

    @RequestMapping("/findIsAllow")
    @ApiOperation(value = "查找 是否允许通过通讯录找到我 的状态 (1允许；2不允许)", httpMethod = "GET",notes = "查找 是否允许通过通讯录找到我 的状态 (1允许；2不允许)")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header",name="token",dataType="String",required=true,value="token"),
            @ApiImplicitParam(paramType = "query",name = "id",value = "用户id",required = true,dataType = "String"),
    })
    public ServerResponse findIsAllowAddressBook(String id){
        return addressBookService.findIsAllowAddressBook(id);
    }

    /**
     * 匹配通讯录
     * @param id 用户id
     * @param listAddressBook 用户手机里的通讯录集合
     * @return
     */
    @RequestMapping("/matchingAddressBook")
    @ApiOperation(value = "匹配通讯录", httpMethod = "GET",notes = "匹配通讯录")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header",name="token",dataType = "String",required = true,value = "token"),
            @ApiImplicitParam(paramType = "query",name = "id",value = "用户id",required = true,dataType = "Integer"),
            @ApiImplicitParam(paramType = "query",name = "listAddressBook",value = "用户的通讯录",required = true,dataType = "String"),
    })
    public ServerResponse matchingAddressBook(Integer id, String listAddressBook){
        return addressBookService.matchingAddressBook(id,listAddressBook);
    }
}
