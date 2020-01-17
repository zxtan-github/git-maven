package org.ifunq.tanzx.mybatis.typehander;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MyTypeHandler implements TypeHandler<TypeHandlerUserAddress> {

    @Override
    public void setParameter(PreparedStatement ps, int i, TypeHandlerUserAddress parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, JSONObject.toJSONString(parameter));
    }

    @Override
    public TypeHandlerUserAddress getResult(ResultSet rs, String columnName) throws SQLException {
        return JSONObject.parseObject(rs.getString(columnName), TypeHandlerUserAddress.class);
    }

    @Override
    public TypeHandlerUserAddress getResult(ResultSet rs, int columnIndex) throws SQLException {
        return JSONObject.parseObject(rs.getString(columnIndex), TypeHandlerUserAddress.class);
    }

    @Override
    public TypeHandlerUserAddress getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return JSONObject.parseObject(cs.getString(columnIndex), TypeHandlerUserAddress.class);
    }
}
