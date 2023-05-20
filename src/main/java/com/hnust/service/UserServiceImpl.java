package com.hnust.service;

import com.hnust.mapper.UserMapper;
import com.hnust.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    private UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public User queryUserByName(String email) {
        return userMapper.queryUserByName(email);
    }
}
