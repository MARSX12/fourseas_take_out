package com.hy.fourseas.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hy.fourseas.mapper.UserMapper;
import com.hy.fourseas.entity.User;
import com.hy.fourseas.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService{
}
