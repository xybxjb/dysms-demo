package cn.slipbend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 针对动态的评论 及 [针对动态的评论]的评论
 */
@Data
@ComponentScan
@Repository
public class Comment implements Serializable {

  private long id;
  private Dynamic dynamic;
  private User user;
  private String text;
  private Comment parent;
  private long hot;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(timezone = "GMT+8", pattern = "yyyy/MM/dd") //出参
  private Date createDate;

  /**
   * 我为该条评论增添热度了吗
   */
  private Boolean hasMyHot;

  /**
   * 子评论(评论的评论)数量
   */
  private Integer numSubComment;
  /**
   * 子评论
   */
  private List<Comment> subComment;

}
