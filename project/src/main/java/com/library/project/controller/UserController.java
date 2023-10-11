package com.library.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.project.entity.UserInfo;
import com.library.project.repository.UserRepository;
import com.library.project.service.UserInfoService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserInfoService userInfoService;
	
	@GetMapping
	public List<UserInfo>getAllUser(){
		return userRepository.findAll();
	}
	
	@PostMapping
	public String CreateUser(@RequestBody UserInfo userInfo) {
		userInfoService.saveUser(userInfo);
		return "Created";
	}
	
	

}
