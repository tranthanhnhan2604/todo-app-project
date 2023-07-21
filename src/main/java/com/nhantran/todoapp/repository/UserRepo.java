package com.nhantran.todoapp.repository;

import com.nhantran.todoapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Integer> {

    Optional<User> findUserByEmailAndPassword(String email, String password);

}
