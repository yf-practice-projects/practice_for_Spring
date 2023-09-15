package com.example.controller;

import java.security.Principal;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.ApikeyConfig;
import com.example.entity.User;
import com.example.service.LoginUserDetailsService;

@Controller
@RequestMapping("/myPage")
public class MyPageController {
	
	@Autowired
	private ApikeyConfig apikeyConfig;
	
	@Autowired
	private LoginUserDetailsService loginUserDetailsService;
	
	@GetMapping
	public ModelAndView myPage(Principal principal) {
		String loginUserId = principal.getName();
		ModelAndView mv = new ModelAndView();
		User user = loginUserDetailsService.findUserByUserId(loginUserId);
	    if(Objects.isNull(user)) {
	    	
	    	mv.setViewName("error");
	    	
	        return mv;
	    }
	    
	    mv.addObject("userDetail", user);
	    
		String gApi = String.format("https://maps.googleapis.com/maps/api/js?key=%s&callback=initMap&v=weekly", apikeyConfig.getGoogleApiKey());
		mv.addObject("googleApiKey", gApi);
		
		mv.setViewName("myPage/myPage");
		return mv;
	}
	

}
