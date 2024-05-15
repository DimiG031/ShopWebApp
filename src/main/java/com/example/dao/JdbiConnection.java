package com.example.dao;

import org.jdbi.v3.core.Jdbi;

public class JdbiConnection {
    private Jdbi jdbi;
    private static JdbiConnection instance;

    private JdbiConnection() {
        String jdbc_str = System.getenv("JDBI_CONNECTION") == null ?
                "jdbc:mysql://localhost:3306/test" : System.getenv("JDBI_CONNECTION");
        String jdbc_user = System.getenv("JDBI_USER") == null ?
                "root" : System.getenv("JDBI_USER");
        String jdbc_password = System.getenv("JDBI_PASSWORD") == null ?
                "" : System.getenv("JDBI_PASSWORD");

        this.jdbi = Jdbi.create(jdbc_str, jdbc_user, jdbc_password);
    }

    public static Jdbi get() {
        if (instance == null) {
            instance = new JdbiConnection();
        }
        return instance.jdbi;
    }
}
