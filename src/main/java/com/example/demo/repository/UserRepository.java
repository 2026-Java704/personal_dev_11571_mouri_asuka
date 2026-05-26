package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	// SELECT * FROM customer WHERE name = ? AND password = ?
	List<User> findByNameAndPassword(String name, String password);

	List<User> findByPassword(String password);

}
