package cn.slipbend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.context.annotation.ComponentScan;

import java.util.Date;
import java.util.List;

@Data
@ComponentScan
public class  RouteRecord {

  private Integer id;
  private User user;
  private Mode mode;
  private Mode parentMode;
  private Double sLongitude;
  private Double sLatitude;
  private Double eLongitude;
  private Double eLatitude;
  private String time;
  private Double avgSpeed;
  private Double speed;
  private Double leng;
  private Double altitude;
  private Long hot;
  private Integer oil;
  @JsonFormat(timezone = "GMT+8", pattern = "yyyy/MM/dd") //出参
  private Date createTime;
  private String imageUrl;
  private Integer mood;
  private String photo;
  private String marks;// 接收前端传来数据
  private List<RouteRecordMark> markList;

  /**
   * 完成次数
   */
  private Integer count;

  /**
   * 排名
   */
  private Integer rank;
  /**
   * 拥堵耗时
   */
  private String congestion;

  /**
   * 我是否为该条行程增添了热度
   */
  private Boolean hasMyHot;

  //我的某个模式的排名
  private Integer myMin;
}
