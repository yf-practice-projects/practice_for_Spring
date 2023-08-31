package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.LoginUserDetails;
import com.example.entity.BulletinBoard;
import com.example.repository.BulletinBoardRepository;
import com.example.service.BulletinBoardService;



@Controller
public class BulletinBoardController {
	
	@Autowired
	BulletinBoardRepository repos; 
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	BulletinBoardService bulletinBoardService;
	
	@GetMapping("/")
	public String redirectToLoginForm() {
		return "redirect:/loginForm"; 
	}
	
	@GetMapping("/list")
	public ModelAndView list(Authentication authentication) {
		ModelAndView mav = new ModelAndView();
		List<BulletinBoard> list = repos.findAll();
		mav.setViewName("bulletinBoard/list");
//		mav.addObject("user", authentication.getPrincipal());
		mav.addObject("data", list);
		return mav;
	}
	
	@GetMapping("/add")
	ModelAndView add() {
		ModelAndView mav = new ModelAndView();
		BulletinBoard data = new BulletinBoard();
		data.setFileName("#");
		mav.addObject("formModel", data);
		mav.setViewName("bulletinBoard/new");
		return mav;
	}
	
	@GetMapping("/edit")
	ModelAndView edit(@RequestParam int id) {
		ModelAndView mav = new ModelAndView();
		BulletinBoard data = bulletinBoardService.findBulletin(id);
		mav.addObject("formModel", data);
		mav.setViewName("bulletinBoard/new");
		return mav;
	}
	
	@GetMapping("/show")
	ModelAndView show(@RequestParam int id) {
		ModelAndView mav = new ModelAndView();
		BulletinBoard data = bulletinBoardService.findBulletin(id);
		mav.addObject("formModel", data);
		mav.setViewName("bulletinBoard/show");
		return mav;
	}
	
	@GetMapping("/apiExecutionCheck/list")
	public String restList() {
		return "apiExecutionCheck/list";
	}
	
	
	/* 更新処理  */
	@PostMapping("/create")
	@Transactional(readOnly=false)
	public ModelAndView save(
			@ModelAttribute("formModel") BulletinBoard bulletinBoard, 
			@RequestParam("file") MultipartFile fileName, 
			@AuthenticationPrincipal LoginUserDetails loginUserDetails) {
		bulletinBoardService.saveBulletin(bulletinBoard,fileName,loginUserDetails);
		return new ModelAndView("redirect:/list");
	}
	
	/* 削除処理  */
	@PostMapping("/delete")
	@Transactional(readOnly=false)
	public ModelAndView delete(@RequestParam int id) {
		repos.deleteById(id);
		return new ModelAndView("redirect:/list");
	}
	
//	@PostConstruct
//	public void init() {
//		BulletinBoard data = new BulletinBoard();
//		data.setTitle("初期1");
//		data.setContent("初期内容");
//		data.setFileName("20230829120035_pexels-alex-kozlov-7690275.jpg");
//		User user = new User();
//		user.setUserId("demo");
//		user.setName("デモ");
//		user.setEncodedPassword(passwordEncoder.encode("demo"));
//		data.setUser(user);
//		data.setCreateDate(new Date());
//		repos.saveAndFlush(data);
//	}
}
