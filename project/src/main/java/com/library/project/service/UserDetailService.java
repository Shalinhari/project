package com.library.project.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.library.project.UserInfoUserDetail.UserInfoUserDetail;
import com.library.project.entity.UserInfo;
import com.library.project.exception.ResourceNotFoundException;
import com.library.project.repository.UserRepository;
@Component
public class UserDetailService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserInfo> userInfo = userRepository.findByUsername(username);
		return userInfo.map(UserInfoUserDetail::new).orElseThrow(()-> new ResourceNotFoundException("user_id not found"));
	}
	
	
}