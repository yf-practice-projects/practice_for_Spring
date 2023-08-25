package com.example.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.LoginUserDetails;
import com.example.entity.User;

@ControllerAdvice
public class GlobalControllerAdvice {
	@ModelAttribute("loginUser")
	public User getCurrentUser(Authentication authentication) {
		if (authentication != null && authentication.getPrincipal() instanceof LoginUserDetails) {
			LoginUserDetails userDetails = (LoginUserDetails) authentication.getPrincipal();
            return userDetails.getUser();
        }
        return null; 
	}
}
