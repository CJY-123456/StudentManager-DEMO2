package com.hnust.mapper;

import com.hnust.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    //通过用户名查询用户
    public User queryUserByName(@Param("email") String email);
}




