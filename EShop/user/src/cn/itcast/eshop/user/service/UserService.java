package cn.itcast.eshop.user.service;

import cn.itcast.eshop.common.service.BaseService;
import cn.itcast.eshop.user.entity.User;

public interface UserService extends BaseService {

    User login(User user) throws Exception;
}
