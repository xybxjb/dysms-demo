package cn.slipbend.service;

import cn.slipbend.model.Area;
import cn.slipbend.util.ServerResponse;

public interface AreaServeice {
    Area getByName(String name);
    Area getByNum(String num);

    ServerResponse getArea();

    ServerResponse delArea();

    ServerResponse getAreaTop();

    ServerResponse getSubArea(Integer id);
}
