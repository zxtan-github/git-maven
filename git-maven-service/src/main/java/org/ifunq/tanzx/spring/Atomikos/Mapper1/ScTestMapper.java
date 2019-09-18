package org.ifunq.tanzx.spring.Atomikos.Mapper1;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ScTestMapper {
    @Insert("INSERT INTO sc_test (id,longId,is_delete) VALUES(#{id},#{longId},#{isDelete})")
    public void insert(@Param("id") Integer id, @Param("longId") String longId, @Param("isDelete") Integer isDelete);
}
