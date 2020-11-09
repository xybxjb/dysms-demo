package cn.slipbend.model;

import lombok.Data;
import org.springframework.context.annotation.ComponentScan;

@Data
@ComponentScan
public class UserPosition {

  private long id;
  private User userId;
  private double lon;
  private double lat;
  private String num;
  private String name;

}
