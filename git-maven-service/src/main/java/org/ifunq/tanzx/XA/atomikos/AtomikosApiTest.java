package org.ifunq.tanzx.XA.atomikos;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.jdbc.AtomikosDataSourceBean;

import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Properties;

public class AtomikosApiTest {

    private static AtomikosDataSourceBean createXaDataSourceBean(String dbUrl,String dbName, String name,String password) {
        // 连接池基本属性
        Properties p = new Properties();
        p.setProperty("url", dbUrl);
        p.setProperty("user", name);
        p.setProperty("password", password);

        // 使用AtomikosDataSourceBean封装com.mysql.jdbc.jdbc2.optional.MysqlXADataSource
        AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
        //atomikos要求为每个AtomikosDataSourceBean名称，为了方便记忆，这里设置为和dbName相同
        ds.setUniqueResourceName(dbName);
        ds.setXaDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlXADataSource");
        ds.setXaProperties(p);
        return ds;
    }
    public static void main(String[] args) throws SystemException {
        AtomikosDataSourceBean ds1 = createXaDataSourceBean("jdbc:mysql://10.32.156.157:3306/haitao_test",
                "haitao_test", "sfhaitao", "sfhaitaodev");
        AtomikosDataSourceBean ds2 = createXaDataSourceBean("jdbc:mysql://10.32.156.157:3306/fangqu_wow_logistics",
                "fangqu_wow_logistics", "sfhaitao", "sfhaitaodev");
        Connection conn1 = null;
        Connection conn2 = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        UserTransaction userTransaction = new UserTransactionImp();
        try {
            conn1 = ds1.getConnection();
            conn2 = ds2.getConnection();
            // JTA规范中的UserTransaction.begin
            userTransaction.begin();
            ps1 = conn1.prepareStatement("INSERT INTO `haitao_test`.`sc_test` (`id`, `longId`, `is_delete`) VALUES ('4', '2', '1')");
            ps1.execute();
            ps2 = conn2.prepareStatement("UPDATE `fangqu_wow_logistics`.`brand` SET `story`='XXX' WHERE id='1';");
            ps2.execute();
            // JTA规范中的UserTransaction.commit
            userTransaction.commit();
        } catch (Exception e) {
            userTransaction.rollback();
        } finally {
            try {
                // 关闭各个流
                if (conn1 != null ) conn1.close();
                if (conn2 != null ) conn2.close();
                if (ps1 != null ) ps1.close();
                if (ps2 != null ) ps2.close();
                if (ds1 != null ) ds1.close();
                if (ds2 != null ) ds2.close();
            } catch (Exception e) {}
        }
    }
}
