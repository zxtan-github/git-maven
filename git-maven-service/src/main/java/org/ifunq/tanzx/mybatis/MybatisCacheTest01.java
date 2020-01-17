package org.ifunq.tanzx.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.ifunq.tanzx.spring.DataJpa.User;

import java.io.IOException;
import java.io.Reader;

public class MybatisCacheTest01 {

    public static void main(String[] args) {
        String resource = "mybatis/Configuration.xml";
        Reader reader;
        try {
            reader = Resources.getResourceAsReader(resource);
            SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder().build(reader);
            SqlSession session1 = sqlMapper.openSession();
            SqlSession session2 = sqlMapper.openSession();
            try {
                User user = session1.selectOne("XXXXXX.UserMapper.getUserById", 6);
                System.out.println(user);

                user.setAddress("重庆");
                session2.update("XXXXXX.UserMapper.getUserById", user);
                session2.selectOne("XXXXXX.UserMapper.getUserById", 6);
                System.out.println(user);

                user = session1.selectOne("XXXXXX.UserMapper.getUserById", 6);
                System.out.println(user);

            } finally {
                session1.close();
                session2.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
