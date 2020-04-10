package cn.jxy.user.service;

import cn.jxy.user.mapper.UserMapper;
import cn.jxy.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 加鑫宇
 * @date 2020-04-10 22:29
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;
    public User queryById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
