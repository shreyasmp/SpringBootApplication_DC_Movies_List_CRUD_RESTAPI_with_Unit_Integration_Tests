package com.shreyas.repository;

import com.shreyas.user.MovieDBUser;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<MovieDBUser, Long>{

    MovieDBUser findByUserName(String username);
}
