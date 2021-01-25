package com.example.demo.repository;

import java.util.List;

import com.example.demo.model.Login;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LoginRepository extends JpaRepository<Login, Long>, JpaSpecificationExecutor<Login> {
    List<Login> findByUsername(String username);
    Login findOneByUsername(String username);
}
