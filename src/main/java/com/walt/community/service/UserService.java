package com.walt.community.service;

import com.walt.community.mapper.UserMapper;
import com.walt.community.model.User;
import com.walt.community.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;

/**
 * @author: walt1012
 * @date: 2020/5/20
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;


    public void createOrUpdate(User user) {
        UserExample example = new UserExample();
        example.createCriteria().andAccountIdEqualTo(user.getAccountId());
        List<User> userList = userMapper.selectByExample(example);

        if (userList.size() == 0) {
            //插入
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        } else {
            //更新
            User dbUser = userList.get(0);
            User updateUser = new User();
            updateUser.setGmtModified(System.currentTimeMillis());
            updateUser.setAvatarUrl(updateUser.getAvatarUrl());
            updateUser.setName(user.getName());
            updateUser.setToken(user.getToken());
            UserExample userExample = new UserExample();
            userExample.createCriteria().andIdEqualTo(dbUser.getId());
            userMapper.updateByExampleSelective(updateUser, userExample);
        }
    }
}
