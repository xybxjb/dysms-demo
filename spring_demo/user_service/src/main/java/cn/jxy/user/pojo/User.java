package cn.jxy.user.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author 加鑫宇
 * @date 2020-04-10 22:03
 */
@Table(name="user")
@Data
public class User {

    /*useGeneratedKeys 参数只针对 insert 语句生效，默认为 false。
    当设置为 true 时，表示如果插入的表以自增列为主键，则允许 JDBC
    支持自动生成主键，并可将自动生成的主键返回。
    */

    @Id
    @KeySql(useGeneratedKeys= true)
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
