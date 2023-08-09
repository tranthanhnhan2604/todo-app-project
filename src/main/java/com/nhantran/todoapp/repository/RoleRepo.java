package com.nhantran.todoapp.repository;

import com.nhantran.todoapp.entity.ERole;
import com.nhantran.todoapp.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RoleRepo extends JpaRepository<Role,Integer> {
    Optional<Role> findByName(ERole name);
}
