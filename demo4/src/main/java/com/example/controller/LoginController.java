package com.example.controller;


import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.entity.User;
import com.example.repository.BulletinBoardRepository;
import com.example.repository.UserRepository;
import com.example.service.LoginUserDetailsService;

import jakarta.annotation.PostConstruct;

@Controller
public class LoginController {

	
	@Autowired
	UserRepository userRepos;
	@Autowired
	BulletinBoardRepository repos; 
	@Autowired
	PasswordEncoder passwordEncoder;
	
	private final LoginUserDetailsService loginUserDetailsService;
	
	public LoginController(LoginUserDetailsService loginUserDetailsService) {
	    this.loginUserDetailsService = loginUserDetailsService;
	}
	
	@GetMapping(path = "loginForm")
	String loginForm() {
		return "loginForm";
	}
	
	@GetMapping(path = "signUp")
	public ModelAndView signUp() {
		ModelAndView mv = new ModelAndView();
		User user = new User();
		mv.addObject("signUpForm", user);
		mv.setViewName("signUpForm");
		return mv;
	}
	
	/* 新規ユーザ登録処理  */
	@PostMapping("/signUp")
	@Transactional(readOnly=false)
	public ModelAndView save(
			@ModelAttribute("signUpForm") User user) {
		String beforePass = user.getEncodedPassword();
		user.setEncodedPassword(passwordEncoder.encode(user.getEncodedPassword()));
		userRepos.saveAndFlush(user);
//		loginUserDetailsService.registerAutoLogin(user);
//		autoLogin(user.getUserId(), beforePass);
		return new ModelAndView("redirect:/list");
	}
//	private void autoLogin(String userid, String password) {
//		UserDetails userDetails = loginUserDetailsService.loadUserByUsername(userid);
//	    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
//	    token.getPrincipal();
//	    if (token.isAuthenticated()) {
//	        SecurityContextHolder.getContext().setAuthentication(token);
//	    }
//	}
	
	/* 初期データ作成 ユーザ:demo パスワード:demo */
    @PostConstruct
    public void init() {
        User user1 = new User();
        user1.setUserId("demo");
        user1.setName("デモ");
        user1.setEncodedPassword(passwordEncoder.encode("demo"));
        userRepos.saveAndFlush(user1);
    }
	
}
