package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorPageController {
	
	@GetMapping("/error")
	public String toErrorPage() {
		return "redirect:/loginForm";
	}
}
