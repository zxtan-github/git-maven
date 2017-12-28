package org.ifunq.tanzx.spring.JDBC;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.StatementCallback;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * JdbcTemplateTest01
 *
 * @author tanzx [tanzongxi@ifunq.com]
 * @date 2017-12-27 16:44
 **/
public class JdbcTemplateTest01 {

    public static void main(String[] args) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        class MyStatementCallback implements StatementCallback {
            @Override
            public Object doInStatement(Statement stmt) throws SQLException, DataAccessException {
                return stmt.execute("select id,name from test");
            }
        }
        Object result = jdbcTemplate.execute(new MyStatementCallback());
    }
}