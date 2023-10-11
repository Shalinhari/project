package com.library.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.project.entity.UserInfo;

@Repository 
public interface UserRepository extends JpaRepository<UserInfo,Integer>{
	
	public Optional<UserInfo>findByUsername(String username);

}
