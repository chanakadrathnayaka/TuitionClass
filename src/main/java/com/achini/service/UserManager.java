package com.achini.service;

import com.achini.dataaccess.UserDataAccess;
import com.achini.models.User;

/**
 * @author Chanaka Rathnayaka
 */
public class UserManager {
    private UserDataAccess userDataAccess;

    public UserManager() {
        userDataAccess = new UserDataAccess();
    }

    public int registerUser(User user) {
        return userDataAccess.insertUser(user);
    }

    public User getUser(User user){
        return userDataAccess.getUser(user);
    }
}
