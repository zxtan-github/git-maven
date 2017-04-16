package org.ifunq.tanzx.threadlocal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnectionManager {

    private  static ThreadLocal<Connection> connectionHolder  = new ThreadLocal<Connection>() {
        @Override
        protected Connection initialValue() {
            Connection conn = null;
            try {
                conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/test", "username",
                        "password");
            } catch (SQLException e) {
            }
            return conn;
        }
    };
    public static Connection getConnection () {
        return connectionHolder.get();
    }
}
