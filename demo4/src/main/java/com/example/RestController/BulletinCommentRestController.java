package com.example.RestController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.BulletinComment;
import com.example.service.BulletinCommentService;

@RestController
@RequestMapping("/api/bulletinComment")
public class BulletinCommentRestController {
	
	@Autowired
	BulletinCommentService bulletinCommentService;
	
	@GetMapping("/list")
	public List<BulletinComment> findCommentList(@RequestParam int id) {
		List<BulletinComment> list = bulletinCommentService.findComment(id);
		
		return list;
	}
	
	@PostMapping("/add/{bulletinId}")
	public HttpStatus addComment(@PathVariable int bulletinId, BulletinComment comment, Authentication authentication) {
		bulletinCommentService.addComment(comment);
		return HttpStatus.OK;
	}
	
	
}
