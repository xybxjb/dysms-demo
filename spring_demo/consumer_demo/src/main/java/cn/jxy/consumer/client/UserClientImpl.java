package cn.jxy.consumer.client;

import cn.jxy.consumer.pojo.User;

/**
 * @author 加鑫宇
 * @date 2020-04-11 18:57
 */
public class UserClientImpl implements UserClient {
    @Override
    public User queryById(Long id) {
        User user = new User();
        user.setName("未知用户");
        return null;
    }
}
