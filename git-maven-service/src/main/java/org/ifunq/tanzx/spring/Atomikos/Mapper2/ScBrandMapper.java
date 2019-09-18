package org.ifunq.tanzx.spring.Atomikos.Mapper2;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ScBrandMapper {
    @Update("UPDATE brand SET story=#{story} WHERE id=#{id}")
    public void update(@Param("id")String id, @Param("story")String story);
}
