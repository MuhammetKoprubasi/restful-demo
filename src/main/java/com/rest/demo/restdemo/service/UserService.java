package com.rest.demo.restdemo.service;

import com.rest.demo.restdemo.exception.UserNotFoundException;
import com.rest.demo.restdemo.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class UserService {

    private static List<User> userList = new ArrayList<>();

    private static int userIdCounter = 3;

    static {
        userList.add(new User(1,"name1", new Date()));
        userList.add(new User(2,"name2", new Date()));
        userList.add(new User(3,"name3", new Date()));
    }

    public List<User> findAll(){
        return userList;
    }

    public User save(User user){
        user.setId(++userIdCounter);
        userList.add(user);
        return user;
    }

    public User getUserById(int id){
        return userList.stream().filter(u -> u.getId() == id).findFirst().orElseThrow(() -> new UserNotFoundException("id:" + id) );
    }

    public boolean deleteById(int id){
        User userFound = userList.stream().filter(u -> u.getId() == id).findFirst().orElse(null);

        if(userFound == null)
            throw new UserNotFoundException("id:"+id);

        userList.remove(userFound);
        return true;
    }


}
