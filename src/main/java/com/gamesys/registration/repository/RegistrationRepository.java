package com.gamesys.registration.repository;


import com.gamesys.registration.domain.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRepository extends CrudRepository<UserEntity, Long> {

    UserEntity findByUsername(String username);
}
