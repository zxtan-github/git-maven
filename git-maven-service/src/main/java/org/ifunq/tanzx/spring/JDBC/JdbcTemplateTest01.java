package org.ifunq.tanzx.spring.JDBC;

import org.ifunq.tanzx.spring.JDBC.mybatis.DO.SkuBarcodeDO;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.StatementCallback;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * JdbcTemplateTest01
 *
 * @author tanzx [tanzongxi@ifunq.com]
 * @date 2017-12-27 16:44
 **/
public class JdbcTemplateTest01 {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/JDBC/JdbcTemplateTest01.xml");
        JdbcTemplate jdbcTemplate = new JdbcTemplate((DataSource) context.getBean("dataSource"));
        class MyStatementCallback implements StatementCallback {
            @Override
            public Object doInStatement(Statement stmt) throws SQLException, DataAccessException {
                return stmt.execute("select id,barcode from sc_sku_barcode");
            }
        }
        Object result = jdbcTemplate.execute(new MyStatementCallback());
        System.out.println(result);

        // 获得mapl类型list
        List mapList = jdbcTemplate.queryForList("select * from sc_sku_barcode");
        System.out.println(mapList);

        // 获得DO类型list
        List<SkuBarcodeDO> doList = jdbcTemplate.query("select * from sc_sku_barcode", new BeanPropertyRowMapper(SkuBarcodeDO.class));
        System.out.println(doList);

    }
}

