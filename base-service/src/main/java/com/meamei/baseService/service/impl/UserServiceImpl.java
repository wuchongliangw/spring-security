package com.meamei.baseService.service.impl;

import com.meamei.baseDao.dao.UserMapper;
import com.meamei.baseEntity.model.User;
import com.meamei.baseService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author mm013
 * @create 2020-04-15 17:46:19
 * @description:
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User, Long> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    public void setBaseMapper(UserMapper userMapper) {
        super.setBaseMapper(userMapper);
    }
}
