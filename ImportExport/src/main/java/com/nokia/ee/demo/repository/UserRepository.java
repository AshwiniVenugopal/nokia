package com.nokia.ee.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nokia.ee.demo.Model.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
	Users findByUsername(String username);

}
