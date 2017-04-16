package org.ifunq.tanzx.threadlocal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TestThreadLocalDao {

    private static ThreadLocal<Connection> conn = new ThreadLocal<Connection>() {
        @Override
        protected Connection initialValue() {
            Connection newConn = null;
            try {
                newConn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/test", "username",
                        "password");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return newConn;
        }
    };

    public static Connection getConnection() throws SQLException {
        return conn.get();
    }

    public void select () throws SQLException {
        Statement stat = TestConnectionManager.getConnection().createStatement();
        // do something ...
    }
    public void insert () throws SQLException {
        Statement stat = TestConnectionManager.getConnection().createStatement();
        // do something ...
    }
    public void delete () throws SQLException {
        Statement stat = TestConnectionManager.getConnection().createStatement();
        // do something ...
    }
}
