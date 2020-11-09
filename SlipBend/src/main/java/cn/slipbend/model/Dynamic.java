package cn.slipbend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Data
@Repository
@ComponentScan
public class Dynamic {

  private Integer id;
  private User userId;
  private String text;
  private String imgsUrl;
  private Integer good;
  private Integer stepOn;
  private String power;
  private double lon;
  private double lat;
  @JsonFormat(timezone = "GMT+8", pattern = "yyyy/MM/dd") //出参
  private Date createDate;
  private Fans fans;
  private Integer views;
}

