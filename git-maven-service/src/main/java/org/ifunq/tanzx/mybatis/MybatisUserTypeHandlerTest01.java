package org.ifunq.tanzx.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.ifunq.tanzx.mybatis.typehander.TypeHandlerUser;
import org.ifunq.tanzx.mybatis.typehander.TypeHandlerUserAddress;

import java.io.IOException;
import java.io.Reader;

public class MybatisUserTypeHandlerTest01 {

    public static void main(String[] args) {
        String resource = "mybatis/Configuration.xml";
        Reader reader;
        try {
            reader = Resources.getResourceAsReader(resource);
            SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder().build(reader);
            SqlSession session = sqlMapper.openSession();
            try {
                TypeHandlerUser userParam = new TypeHandlerUser();
                userParam.setName("tangyan999");
                userParam.setPhone("18623196060");
                TypeHandlerUserAddress address = new TypeHandlerUserAddress();
                address.setProvince("重庆");
                address.setCity("重庆市");
                address.setDistrict("渝北区");
                address.setAddress("红黄路9999号");
                userParam.setAddress(address);
                session.insert("ZZZZZZ.UserMapper.insert", userParam);
                session.commit();
            } finally {
                session.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
