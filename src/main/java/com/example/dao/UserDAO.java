package com.example.dao;

import com.example.models.User;

public class UserDAO {

    public static boolean exists(String username) {
        try {
            JdbiConnection.get().withHandle(handle ->
                    handle.createQuery("SELECT user_id FROM users WHERE username = :username")
                            .bind("username", username).mapTo(int.class).first());
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public static User one(String username) {
        return JdbiConnection.get().withHandle(handle ->
                handle.createQuery("SELECT * FROM users WHERE username = :username")
                        .bind("username", username).mapToBean(User.class).first());
    }
}
