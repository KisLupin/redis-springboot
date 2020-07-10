package com.lupin.redis.service;

import com.lupin.redis.model.User;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private RedisTemplate<String, User> redisTemplate;

    private HashOperations hashOperations;


    public UserServiceImpl(RedisTemplate<String, User> redisTemplate) {
        this.redisTemplate = redisTemplate;

        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void save(User user) {
        hashOperations.put("NAME", user.getId(), user);
    }

    @Override
    public Map<String, User> findAll() {
        return hashOperations.entries("NAME");
    }

    @Override
    public User findById(String id) {
        return (User)hashOperations.get("NAME", id);
    }

    @Override
    public void update(User user) {
        save(user);
    }

    @Override
    public void delete(String id) {

        hashOperations.delete("NAME", id);
    }
}
