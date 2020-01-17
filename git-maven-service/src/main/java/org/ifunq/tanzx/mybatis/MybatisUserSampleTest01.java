package org.ifunq.tanzx.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.ifunq.tanzx.spring.DataJpa.User;

import java.io.IOException;
import java.io.Reader;

public class MybatisUserSampleTest01 {

    public static void main(String[] args) {
        String resource = "mybatis/Configuration.xml";
        Reader reader;
        try {
            reader = Resources.getResourceAsReader(resource);
            SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder().build(reader);
            SqlSession session = sqlMapper.openSession();
            try {
                User user = (User) session.selectOne("XXXXXX.UserMapper.getUser", 1);
                System.out.println(user);
            } finally {
                session.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
