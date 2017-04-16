package org.ifunq.tanzx.threadlocal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDao {

    private Connection conn;
    public  TestDao () {
        try {
            this.conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/test", "username",
                    "password");
        } catch (SQLException e) {};
    }
    public  TestDao (Connection conn) {
        this.conn = conn;
    }
    public void select () throws SQLException {
        Statement stat = conn.createStatement();
        // do something ...
    }
    public void insert () throws SQLException {
        Statement stat = conn.createStatement();
        // do something ...
    }
    public void delete () throws SQLException {
        Statement stat = conn.createStatement();
        // do something ...
    }
}
