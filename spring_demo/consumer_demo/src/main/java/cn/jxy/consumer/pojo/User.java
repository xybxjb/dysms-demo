package cn.jxy.consumer.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @author 加鑫宇
 * @date 2020-04-10 23:02
 */
@Data
public class User {
    private Long id;
    private String userName; //用户名
    private String password; //密码
    private String name; //姓名
    private Integer age; //年龄
    private Integer sex; //性别 1男 2女
    private Date birthday; //出生日期
    private Date created; //创建时间
    private Date updated; //更新时间
    private String note; //备注
}
