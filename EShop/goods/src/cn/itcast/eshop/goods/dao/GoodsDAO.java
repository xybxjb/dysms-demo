package cn.itcast.eshop.goods.dao;

import cn.itcast.eshop.common.dao.BaseDAO;
import cn.itcast.eshop.goods.entity.Goods;

import java.util.List;

public interface GoodsDAO extends BaseDAO {

    List<Goods> getEntityList() throws Exception;
}
