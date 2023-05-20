package com.hnust.service;
import com.hnust.pojo.User;
public interface UserService {
        //通过用户名查询用户
        public User queryUserByName(String email);
}
