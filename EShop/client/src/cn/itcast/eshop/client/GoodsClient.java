package cn.itcast.eshop.client;

import cn.itcast.eshop.common.entity.Msg;
import cn.itcast.eshop.common.util.JSONUtil;
import cn.itcast.eshop.goods.action.GoodsAction;
import cn.itcast.eshop.goods.entity.Goods;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoodsClient extends Client {

    /** 商品控制器类 */
    private GoodsAction goodsAction = new GoodsAction();

    Map<String, Goods> code2Goods = new HashMap<>();

    /**
     * 商品管理首页
     *
     * 商品页面跳转控制器：
     *  本模块的操作由此方法控制跳转
     *  其它模块的操作返回上层，由Client控制跳转
     * @return 返回公共操作
     */
    public String index() {
        showGoodsList(); // 展示商品列表
        String result = userOperate("请根据序号查看商品详情", "L登录", "I首页；");
        while(true) {
            if(result.equals(LOGIN)) {
                return LOGIN;
            }
            if(result.equals(EXIT)) {
                return EXIT;
            }
            if(result.equals(INDEX)) {
                return INDEX;
            }
            Goods goods = code2Goods.get(result); // get方法返回结果，数据或null
            if(goods != null) { // 查看详情
                currentGoods = goods;
                showGoodsDetail();
                result = userOperate("输入A加入购物车", "L登录", "I首页；");
            } else if(result.equals(ADD)) { // 加入购物车
                return ADD;
            } else {
                System.out.println("输入错误，请重新输入");
                result = userOperate("请根据序号查看商品详情", "L登录；");
            }
        }
    }

    /**
     * 展示商品列表
     *  1.向后台发送请求，获取商品数据
     *  2.解析响应消息字符串
     *  3.展示商品列表
     */
    public void showGoodsList() {
        // 1.向后台发送请求，获取商品数据
        String msg = goodsAction.getGoodsList();
        // 2.解析响应消息字符串
        Msg msgObj = JSONUtil.JSON2Entity(msg, Msg.class);
        Object obj = msgObj.getObj(); // [{}, {}]
        List<?> goodsListObj = (List<?>) obj;
        System.out.println("【商品列表】");
        System.out.println("编号\t\t商品名称\t\t单价\t\t库存");
        System.out.println("----------------------------------");
        int index = 1; // 编号
        for (Object o : goodsListObj) {
            // o : {...}
            String goodsJson = o.toString(); // {,,,}
            Goods goods = JSONUtil.JSON2Entity(goodsJson, Goods.class);
            String num = goods.getNumber() + ""; // 库存
            String name = goods.getName(); // 名称
            String price = goods.getPrice() + ""; // 单价
            // 3.展示商品列表
            System.out.println(index + ".\t\t" + name + "\t\t\t" + price + "\t\t" + num);
            code2Goods.put(index + "", goods);
            index ++;
        }
    }

    /**
     * 查看商品详情
     * 1.通过数据ID获取数据，然后进行展示
     * 2.在展示商品的时候，把商品列表存储起来，然后，当选择商品编号，就把对应的商品展示出来
     *  Map<String, Goods> :key --> 编号，value --> Goods对象
     */
    public void showGoodsDetail() {
        System.out.println("【商品详情】");
        System.out.println("编号\t\t商品名称\t\t单价\t\t库存\t\t品牌");
        System.out.println("----------------------------------");
        String num = currentGoods.getNumber() + ""; // 库存
        String name = currentGoods.getName();       // 名称
        String price = currentGoods.getPrice() + "";// 单价
        String brand = currentGoods.getBrand();     // 品牌
        // 3.展示商品列表
        System.out.println(1 + ".\t\t" + name + "\t\t\t" + price + "\t\t" + num + "\t\t" + brand);

    }
}
