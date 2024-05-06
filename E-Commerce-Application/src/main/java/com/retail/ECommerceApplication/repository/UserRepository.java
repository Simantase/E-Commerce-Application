package com.retail.ECommerceApplication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.retail.ECommerceApplication.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User,Integer>{
	boolean existsByEmail(String email);
	Optional<User> findByUserName(String username);


}
