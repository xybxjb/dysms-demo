package cn.slipbend.service.impl;

import cn.slipbend.dao.FollowDao;
import cn.slipbend.dao.UserDao;
import cn.slipbend.enums.RelationshipEnum;
import cn.slipbend.model.User;
import cn.slipbend.service.NearService;
import cn.slipbend.service.UserService;
import cn.slipbend.util.DateUtil;
import cn.slipbend.util.ServerResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Date:
 * @Description: 用户操作
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private NearService nearService;

    @Autowired
    private FollowDao followDao;

    //修改用户个人资料
    @Override
    public Object updateUserInfo(User user) {
        Map<String,Object> res = new HashMap<>();
        userDao.updateUserInfoById(user);
        res.put("msg","修改成功");
        //返回user信息
        User newUser = userDao.findUserBaseInfoBy(user.getId());
        res.put("user",newUser);
        return res;
    }

    /**
     * 修改车辆信息
     * @param id 用户id
     * @param carModel 汽车型号
     * @param carAge 车龄
     * @param engineModel 引擎型号
     * @return 用户的车辆信息
     */
    @Override
    public Object updateCarInfo(Integer id, String carModel, Integer carAge, String engineModel) {
        Map<String,Object> res = new HashMap<>();
        userDao.updateCarInfo(id,carModel,carAge,engineModel);
        Date date = new Date();
        //新建日期格式
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //将日期对象格式化成指定格式并以String输出
        String updateTime = simpleDateFormat.format(date);
        res.put("msg","修改成功");
        res.put("user", userDao.getCarInfo(id));
        res.put("updateTime",updateTime);
        return res;
    }

    //计算用户行驶总距离
    @Override
    public Object findUserDrivingLength(Integer id) {
        Map<String,Object> res = new HashMap<>();
        Integer length = userDao.findUserAllLength(id);
        res.put("allLength",length);
        return res;
    }

    /**
     * 用户行驶到达一系列的公里数的相应日期
     * @param userId
     * @param mileage
     * @return
     */
    @Override
    public ServerResponse toLevelDate(Integer userId, String[] mileage) {
        try{
            Map<String, Object> result = new HashMap<>();
            for(String m:mileage){
                String date = userDao.getToLevelDate(userId, m);
                if(date != null){
                    date = StringUtils.substringBefore(date, " ");
                }
                result.put(m,date);
            }
            return ServerResponse.getSuccess("获取数据成功",result);
        }catch (Exception e){
            e.printStackTrace();
            return ServerResponse.getError("获取数据失败");
        }
    }

    //查找达成里程成就的人数
    @Override
    public Object findCompletePeopleByMileage(Double mileage) {
        Map<String,Object> res = new HashMap<>();
        Integer nums = userDao.findCompletePeopleByMileage(mileage);
        res.put("nums",nums);
        return res;
    }
    //查看用户关注列表
    @Override
    public Object findUserFollow(Integer id) {
        Map<String,Object> res = new HashMap<>();
        List<User> userList = userDao.findUserFollow(id);
        for (User user:userList) {
            Integer myFan = followDao.findFollow(id,user.getId());
            if(1 == myFan){
                user.setRelationship(RelationshipEnum.FRIEND);
            }else {
                user.setRelationship(RelationshipEnum.FOLLOWED);
            }
        }
        res.put("follow",userList);
        return res;
    }
    //查看用户粉丝列表
    @Override
    public Object findUserFans(Integer id) {
        Map<String,Object> res = new HashMap<>();
        List<User> userList = userDao.findUserFans(id);
        for (User user:userList) {
            Integer myFollow = followDao.findFollow(user.getId(),id);
            if(1 == myFollow){
                user.setRelationship(RelationshipEnum.FRIEND);
            }else {
                user.setRelationship(RelationshipEnum.FOLLOW_BACK);
            }
        }
        res.put("fans",userList);
        return res;
    }

    /**
     * 查看用户个人信息
     * 部分1：用户基础信息
     * 部分2：行程数 关注数 粉丝数 动态数 行驶总里程 本周耗油 车辆型号 上次记录距今多少天
     * @param userId
     * @param isBaseInfo
     * @return
     */
    @Override
    public Object findUserInfo(Integer userId, Boolean isBaseInfo) {
        Map<String,Object> res = new HashMap<>();
        //用户基础信息
        User user = userDao.findUserBaseInfoBy(userId);
        res.put("user",user);
        if(!isBaseInfo){
            //行程次数
            Integer routeCount = userDao.findCountRoute(userId);
            //关注数
            Integer followCount = userDao.findCountFollow(userId);
            //粉丝数
            Integer fansCount = userDao.findCountFans(userId);
            //动态数
            Integer dynamicCount = userDao.findCountDynamic(userId);
            //总里程
            Integer allLength = userDao.findUserAllLength(userId);
            //总油耗
            Integer allOil = userDao.findUserAllOil(userId);

            res.put("routeCount",routeCount);
            res.put("followCount",followCount);
            res.put("fansCount",fansCount);
            res.put("dynamicCount",dynamicCount);
            res.put("allLength",allLength);
            res.put("allOil",allOil);
        }
        return res;
    }

    //修改第三方登录状态
    @Override
    public ServerResponse updateSignInWith(Integer userId,Integer weChatStatus,Integer qqStatus) {
        try {
            if (weChatStatus == null && qqStatus == null){
                return null;
            }
            // 如果 微信 修改值不为空 且 传入值为 0 或 1
            if(weChatStatus != null && weChatStatus>-1 && weChatStatus <2){
                // 如果 qq 修改值不为空 且 传入值为 0 或 1
                if(qqStatus != null && qqStatus>-1 && qqStatus <2){
                    // 修改 微信 和 qq
                    userDao.updateSignInWith(userId,weChatStatus,qqStatus);
                    return ServerResponse.getSuccess("修改成功");
                }
                // 如果 qq 修改值为空 或 传入值不为 0 或 1
                else{
                    // 只修改微信
                    userDao.updateSignInWith(userId,weChatStatus,null);
                    return ServerResponse.getSuccess("修改微信成功");
                }
            }
            // 如果 微信 修改值为空 或 传入值不为 0 或 1
            else {
                // 如果 qq 修改值不为空 且 传入值为 0 或 1
                if(qqStatus != null && qqStatus>-1 && qqStatus <2){
                    // 只修改 qq
                    userDao.updateSignInWith(userId,null,qqStatus);
                    return ServerResponse.getSuccess("修改qq成功");
                }
                // 如果 qq 修改值为空 或 传入值不为 0 或 1
                else{
                    return ServerResponse.getError("不符合的数据");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.getError("修改失败");
        }
    }

    // 修改接收未关注对象的消息
    public ServerResponse updateAttention(Integer userId,Integer noAttentionStatus) {
        try {
            // 如果 接收未关注对象的消息 且 传入值为 1 或 2
            if(noAttentionStatus !=null){

                if (noAttentionStatus == 1) {
                    userDao.updateAttention(userId, noAttentionStatus);
                } else if(noAttentionStatus == 2) {
                    userDao.updateAttention(userId, noAttentionStatus);
                }else{
                    return ServerResponse.getError("不符合的数据");
                }
            }


            return ServerResponse.getSuccess("修改成功");

        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.getError("修改失败");
        }
    }
    //获取接收未关注对象的状态
    @Override
    public ServerResponse getAttention(Integer userId) {
        try {
            return ServerResponse.getSuccess("查询到了",userDao.getAttention(userId));
        }catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.getError("查询失败");
        }
    }

    //注销账户
    @Override
    @Transactional
    public ServerResponse deleteAccount(Integer userId){

        try {
            //删除mysql的数据
            userDao.deleteAccount(userId);
            //删除redis里面的用户位置信息
            if (nearService.deleteUserPosition(userId).getCode()==0){
                return ServerResponse.getSuccess("删除成功");
            }else {
                throw new RuntimeException("Redis删除用户位置失败");
            }
        }catch (Exception e) {
            e.printStackTrace();
            //回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ServerResponse.getError("删除失败");
        }
    }

    @Override
    public ServerResponse getSignInWith(Integer userId) {
        try {
            return ServerResponse.getSuccess("查询到了",userDao.getSignInWith(userId));
        }catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.getError("查询失败");
        }
    }

    @Override
    public ServerResponse updateNearby(Integer userId, Integer nearbyStatus) {
        try {
            if (nearbyStatus != null) {
                if (nearbyStatus == 1) {
                    userDao.updateNearby(userId, nearbyStatus);
                } else if (nearbyStatus == 2) {
                    userDao.updateNearby(userId, nearbyStatus);
                } else {
                    return ServerResponse.getError("不符合的数据");
                }
            }
            return ServerResponse.getSuccess("修改成功",userDao.getSignNearby(userId));
            } catch(Exception e){
                e.printStackTrace();
                return ServerResponse.getError("修改失败");
            }
    }
    @Override
    public ServerResponse getSignNearby(Integer userId) {
        try {
//            System.out.println(DateUtil.getLastDate("2000-10-28",0,0,-4));
            return ServerResponse.getSuccess("查询到了",userDao.getSignNearby(userId));
        }catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.getError("查询失败");
        }
    }


    // 修改背景图片
    @Override
    public ServerResponse updatebackgroundImage(Integer userId,String backgroudImage) {
        try {
            return ServerResponse.getSuccess("修改成功",userDao.updatebackgroundImage(userId,backgroudImage));
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.getError("修改失败");
        }
    }
}
