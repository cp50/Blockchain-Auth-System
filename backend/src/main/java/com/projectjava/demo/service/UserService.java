package com.projectjava.demo.service;

import com.projectjava.demo.model.User;

public interface UserService {
    String registerUser(User user);
    String loginUser(User user);
}
