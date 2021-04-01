package cn.slipbend.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 行程路线的点标记
 */
@Data
public class RouteRecordMark implements Serializable {

    /**
     * id
     */
    private Integer id;
    /**
     * 行程id
     */
    private RouteRecord routeRecord;
    /**
     * 经度
     */
    private String longitude;
    /**
     * 纬度
     */
    private String latitude;
    /**
     * 海拔
     */
    private String altitude;
    /**
     * 千米(行至此经纬度、海拔的里程)
     */
    private String km;
}
