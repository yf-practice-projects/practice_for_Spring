package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.LoginUserDetails;
import com.example.entity.User;
import com.example.repository.UserRepository;

@Service
public class LoginUserDetailsService implements UserDetailsService{
	
	@Autowired
	UserRepository userRepos;

	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		// TODO 自動生成されたメソッド・スタブ
		
		User user = userRepos.findByUserId(userId);
		if (user == null) {
            throw new UsernameNotFoundException("user not found");
        }
        return new LoginUserDetails(user);
	}
	
	
	
}
