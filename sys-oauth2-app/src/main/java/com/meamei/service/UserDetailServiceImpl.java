package com.meamei.service;

import com.meamei.baseDao.dao.UserMapper;
import com.meamei.baseEntity.model.User;
import com.meamei.model.UserDetailImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

/**
 * @author mm013
 * @create 2020-04-16 15:38:04
 * @description:
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Condition condition = new Condition(User.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andEqualTo("account", username);
        User user = userMapper.selectOneByExample(condition);
        if (user != null) {
            return new UserDetailImpl(user.getAccount(), user.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
        } else {
           throw new UsernameNotFoundException("User " + username + "was not found in the database");
        }
    }
}
