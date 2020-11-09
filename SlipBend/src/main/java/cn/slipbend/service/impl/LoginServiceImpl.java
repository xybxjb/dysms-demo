package cn.slipbend.service.impl;

import cn.slipbend.dao.LoginDao;
import cn.slipbend.model.User;
import cn.slipbend.service.LoginService;
import cn.slipbend.util.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: AnZX
 * @Date: 2020/07/20/18:10
 * @Description:
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginDao loginDao;
    //根据手机号密码登录
    @Override
    public User loginByPassword(String phone, String password) {
        return loginDao.findUserByPhoneAndPassword(phone,password);
    }
    //根据手机号查看用户是否注册
    @Override
    public User findUserByPhone(String phone) {
        return loginDao.findUserByPhone(phone);
    }

    @Override
    public User findUserByWeChatOrQQ(Integer openIdType, String openId) {
        return loginDao.findUserByWeChatOrQQ(openIdType,openId);
    }

    @Override
    public void uptUserWeChatOrQQOpenId(Integer openIdType, String phone, String openId) {
        loginDao.uptUserWeChatOrQQOpenId(openIdType,phone,openId);
    }

    //注册用户
    @Override
    public void insertUser(String phone) {
        loginDao.insertUser(phone);
    }

    @Override
    public void insertUserByWeChatOrQQ(Integer openIdType, String phone, String openId, String username, String icon) {
        loginDao.insertUserByWeChatOrQQ(openIdType,phone,openId,username,icon);
    }

    //修改密码
    @Override
    public void updatePassword(String phone, String newPassword) {
        loginDao.updatePassword(phone,newPassword);
    }
    //修改性别年龄
    @Override
    public ServerResponse updateSexAndAge(String id, String sex, Integer age) {
        try{
            loginDao.updateSexAndAge(id,sex,age);
            Map<String,Object> res = new HashMap<>();
            res.put("msg","修改成功");
            res.put("sex",sex);
            res.put("age",age);
            return ServerResponse.getSuccess("修改成功", res);
        }catch (Exception e){
            e.printStackTrace();
            return ServerResponse.getError("修改失败");
        }

    }
    //修改汽车和发动机型号
    @Override
    public Object updateCarModel(String id, String carModel, String engineModel) {
        Map<String,Object> res = new HashMap<>();
        loginDao.updateCarModel(id,carModel,engineModel);
        res.put("msg","修改成功");
        res.put("carModel",carModel);
        res.put("engineModel",engineModel);
        return res;
    }
    //修改头像和用户名
    @Override
    public ServerResponse updateImageAndUsername(String id, String imageUrl, String username) {
        try{
            loginDao.updateImageAndUsername(id,imageUrl,username);
            Map<String,Object> res = new HashMap<>();
            res.put("msg","修改成功");
            res.put("imageUrl",imageUrl);
            res.put("username",username);
            return ServerResponse.getSuccess("修改成功", res);
        }catch (Exception e){
            e.printStackTrace();
            return ServerResponse.getError("修改失败");
        }
    }


}
