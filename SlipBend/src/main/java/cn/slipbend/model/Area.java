package cn.slipbend.model;

import lombok.Data;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Data
@ComponentScan
@Repository
public class Area implements Serializable {

  private long id;
  private String name;
  private Area parent;
  private long sort;
  private long level;
  private String num;
  private String code;

  private List<Area> subArea;

}

