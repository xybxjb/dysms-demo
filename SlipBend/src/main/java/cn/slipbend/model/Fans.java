package cn.slipbend.model;

import lombok.Data;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author tht
 * @version 1.0
 */
@Data
@ComponentScan
public class Fans {
    private Integer id;
    //关注人id
    private Integer followUserId;
    //粉丝id
    private Integer fansUserId;
    //粉丝数量
    private String fansNum;
}
