package org.ifunq.tanzx.spring.DataJpa;

import org.springframework.data.repository.CrudRepository;

public interface UserCrudRepository extends CrudRepository<User, String> {
}
