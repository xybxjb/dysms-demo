package cn.slipbend.model;

import lombok.Data;
import org.springframework.context.annotation.ComponentScan;

@Data
@ComponentScan

public class IMGroup {
    private Integer id;
    private User userId;
    private String groupId;
}
