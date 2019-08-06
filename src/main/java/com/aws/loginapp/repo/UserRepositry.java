package com.aws.loginapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aws.loginapp.model.User;

public interface UserRepositry extends JpaRepository<User, Integer>{

		User findByEmailAndPassword(String email,String password);
}
