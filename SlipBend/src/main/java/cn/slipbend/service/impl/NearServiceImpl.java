package cn.slipbend.service.impl;

import cn.slipbend.dao.NearDao;
import cn.slipbend.dao.RouteDao;
import cn.slipbend.dao.UserDao;
import cn.slipbend.enums.EachSlipEnum;
import cn.slipbend.model.User;
import cn.slipbend.service.NearService;
import cn.slipbend.util.RedisUtil;
import cn.slipbend.util.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NearServiceImpl implements NearService {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private NearDao nearDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private RouteDao routeDao;

    /**
     * 存入用户位置所用 key
     */
    static final String KEY = "slipBendUserId";

    /**
     * 保存滑向右边的用户时的 key 所加的前缀
     */
    static final String SIGN = "SToR";

    /**
     * 保存用户位置信息
     * @param userId 用户id
     * @param longitude 经度
     * @param latitude 纬度
     * @return
     */
    @Override
    public ServerResponse saveUserPosition(Integer userId, Double longitude, Double latitude) {
        try{
            redisUtil.geoAdd(KEY,longitude,latitude,userId);
            return ServerResponse.getSuccess("位置保存成功");
        }catch (Exception e){
            e.printStackTrace();
            return ServerResponse.getError("位置保存失败");
        }
    }

    /**
     * 删除用户位置
     * @param userId 用户id
     * @return
     */
    @Override
    public ServerResponse deleteUserPosition(Integer userId) {
        try{
            redisUtil.deleteMember(KEY,userId);
            return ServerResponse.getSuccess("删除用户位置成功");
        }catch (Exception e){
            e.printStackTrace();
            return ServerResponse.getError("删除用户位置失败");
        }
    }

    /**
     * 根据当前用户的经纬度，查出给定距离范围内的 TA 的附近的用户（返回的附近用户数量由 count 决定）
     * @param longitude 经度
     * @param latitude 纬度
     * @param distance 距离
     * @param count 查询的数量
     * @return
     */
    @Override
    public ServerResponse nearByXY(Double longitude, Double latitude, Integer distance, Integer count) {
        try{
            return ServerResponse.getSuccess("获取附近成功",redisUtil.redisNearByXY(KEY,longitude,latitude,distance,count));

        }catch (Exception e){
            e.printStackTrace();
            return ServerResponse.getError("获取附近失败");
        }
    }

    /**
     * 获取【附近的人】页面所需的 用户 基本信息 及 百公里加速
     * @param id 用户id
     * @return
     */
    @Override
    public ServerResponse userInfo(Integer id) {
        try{
            User user = userDao.findUserBaseInfoBy(id);

            // 百公里加速
            String time = routeDao.getTime(id);
            String[] split = time.split("");

            for(int i = 0; i<split.length ;i++){
                if(!split[i].equals("0") && !split[i].equals(":")){
                    time = time.substring(i);
                    break;
                }
            }
            time = time.replace(":", ".");
            user.setAccelerationCapability(time);

            return ServerResponse.getSuccess("获取数据成功", user);
        }catch (Exception e){
            e.printStackTrace();
            return ServerResponse.getError("获取用户数据失败");
        }
    }

    /**
     * 保存滑向左边的用户
     * @param userId
     * @param slideLeftUserId
     * @return
     */
    @Override
    public ServerResponse slideToLeft(Integer userId, Integer slideLeftUserId) {
        try{
            nearDao.saveSlideToLeft(userId,slideLeftUserId);
            return ServerResponse.getSuccess("保存被左滑用户成功");
        }catch (Exception e){
            e.printStackTrace();
            return ServerResponse.getError("保存被左滑用户失败");
        }
    }

    /**
     * 保存滑向右边的用户
     * @param userId 用户id
     * @param slideRightUserId 被右滑用户id
     * @return 与右滑用户的关系（是单方面右滑，还是互相右滑）
     */
    @Override
    public ServerResponse slideToRight(Integer userId, Integer slideRightUserId) {
        String myId = SIGN + userId;
        String slideToRightId = SIGN + slideRightUserId;
        try{
            EachSlipEnum eachSlide = null;
            if(redisUtil.hset(myId, slideToRightId, 1)){
                boolean result = redisUtil.hHasKey(slideToRightId, myId);
                if(result){
                    eachSlide = EachSlipEnum.EACH_SLIDE;
                }
                else{
                    eachSlide = EachSlipEnum.MY_SLIDE;
                }
            }
            return ServerResponse.getSuccess("右滑用户成功", eachSlide);
        }catch (Exception e){
            e.printStackTrace();
            return ServerResponse.getError("右滑用户失败");
        }
    }

    /**
     * 获取两个地方的距离
     * @param member1
     * @param member2
     * @return
     */
    @Override
    public ServerResponse geoDist(Object member1, Object member2) {
        try{
            return ServerResponse.getSuccess("获取距离成功",redisUtil.geoDist(KEY,member1,member2));

        }catch (Exception e){
            e.printStackTrace();
            return ServerResponse.getError("获取距离失败");
        }
    }

    /**
     * 从key里返回所有给定位置元素的位置（经度和纬度）
     * @param userId 用户id
     * @return
     */
    @Override
    public ServerResponse getUserPosition(Integer userId) {
        try{
            return ServerResponse.getSuccess("获取用户位置成功",redisUtil.redisGeoGet(KEY,userId));
        }catch (Exception e){
            e.printStackTrace();
            return ServerResponse.getError("获取用户位置失败");
        }
    }

}
