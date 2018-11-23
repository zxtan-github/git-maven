package org.ifunq.tanzx.spring.service;

import org.ifunq.tanzx.spring.bean.User;

public class UserServiceImpl implements UserService {
    @Override
    public User getOneUser() {
        User user = new User();
        user.setName("yanghua");
        user.setAge(28);
        return user;
    }
}
