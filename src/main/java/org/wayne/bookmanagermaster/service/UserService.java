package org.wayne.bookmanagermaster.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wayne.bookmanagermaster.dao.UserDao;
import org.wayne.bookmanagermaster.model.User;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public void addUser(User user){
        userDao.addUser(user);
    }

    public User getUser(int uid){
        return userDao.selectById(uid);
    }

    public User getUser(String email){
        return userDao.selectByEmail(email);
    }
}
