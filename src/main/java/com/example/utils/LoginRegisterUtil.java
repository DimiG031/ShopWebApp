package com.example.utils;

import com.example.dao.UserDAO;
import com.example.models.User;
import org.mindrot.jbcrypt.BCrypt;

public class LoginRegisterUtil {
    public static String hashPassword(String plain_password) {
        return BCrypt.hashpw(plain_password, BCrypt.gensalt());
    }

    public static boolean compare(String plain_password, String hashed_password) {
        return BCrypt.checkpw(plain_password, hashed_password);
    }

    public static User login(String username, String password) {
        if (!UserDAO.exists(username)) {
            return null;
        }
        User user = UserDAO.one(username);
        if (!compare(password, user.getPassword())) {
            return null;
        }
        return user;
    }
}
