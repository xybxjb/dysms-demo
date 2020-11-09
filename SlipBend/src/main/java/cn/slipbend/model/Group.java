package cn.slipbend.model;

import lombok.Data;
import org.springframework.context.annotation.ComponentScan;

import java.util.Date;

@Data
@ComponentScan
public class Group {

  private long id;
  private User userId;
  private String name;
  private long number;
  private long hotLv;
  private String introduce;
  private Date craeteDate;
}
