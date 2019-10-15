package org.ifunq.tanzx.JPA;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends Repository<User, String> {

    List<User> findByName(String name);

    List<User> findAll();

    @Query("select o from User o where name = :name")
    User queryUser(@Param("name") String xxx);

    @Query(nativeQuery = true ,value = "select count(*) from user" )
    int queryUserCount();
}
