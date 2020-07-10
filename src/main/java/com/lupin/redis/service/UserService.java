package com.lupin.redis.service;

import com.lupin.redis.model.User;

import java.util.Map;

public interface UserService {
    void save(User user);
    Map<String, User> findAll();
    User findById(String id);
    void update(User user);
    void delete(String id);
}
