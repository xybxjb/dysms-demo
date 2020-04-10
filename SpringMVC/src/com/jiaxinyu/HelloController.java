package com.jiaxinyu;

import com.jiaxinyu.domin.Account;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author jiaxinyu
 * @date 2020-03-31 19:56
 */
//@Controller("helloController")
@RequestMapping("")
public class HelloController {
    @RequestMapping("/hello")
    public String sayHello() {
        System.out.println("hello Mvc");
        return "success";
    }


    @RequestMapping(value = "/saveAccount", method = RequestMethod.POST)
    public String saveAccount() {
        System.out.println("保存了账户");
        return "success";
    }

    @RequestMapping(value = "/removeAccount", params = {"accountName", "money>100"})
    public String removeAccount() {
        System.out.println("删除了账户");
        return "success";
    }

    @RequestMapping("/saveAccout")
    public String saveAccount(Account account) {
        System.out.println("保存账户。。" + account);
        return "success";
    }

}