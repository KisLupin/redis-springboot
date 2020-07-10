package com.lupin.redis.controller;

import com.lupin.redis.model.User;
import com.lupin.redis.service.UserServiceImpl;
import com.lupin.redis.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class Controller {
    private UserService redisRepository;

    public Controller(UserServiceImpl redisRepository) {
        this.redisRepository = redisRepository;
    }

    @GetMapping("/add/{id}/{name}")
    public User add(@PathVariable("id") final String id,
                    @PathVariable("name") final String name) {
        redisRepository.save(new User(id, name));
        return redisRepository.findById(id);
    }

    @GetMapping("/update/{id}/{name}")
    public User update(@PathVariable("id") final String id,
                       @PathVariable("name") final String name) {
        redisRepository.update(new User(id, name));
        return redisRepository.findById(id);
    }

    @GetMapping("/delete/{id}")
    public Map<String, User> delete(@PathVariable("id") final String id) {
        redisRepository.delete(id);
        return all();
    }

    @GetMapping("/all")
    public Map<String, User> all() {
        return redisRepository.findAll();
    }
}
