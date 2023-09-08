package com.example.RestController;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.LoginUserDetails;
import com.example.entity.BulletinBoard;
import com.example.entity.BulletinComment;
import com.example.entity.User;
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
	
	@PostMapping("/add")
	@Transactional(readOnly=false)
	public HttpStatus addComment(@RequestBody Map<String, Object> jsonObject, @AuthenticationPrincipal LoginUserDetails loginUserDetails) {
		BulletinComment comment = new BulletinComment();
		BulletinBoard bulletin = new BulletinBoard();
		bulletin.setId(Integer.parseInt((String) jsonObject.get("bulletinId")));
		User user = loginUserDetails.getUser();
		
		comment.setBulletinBoard(bulletin);
		comment.setComment((String) jsonObject.get("comment"));
		comment.setUser(user);
		comment.setCreateDate(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
		boolean result = bulletinCommentService.addComment(comment);
		if(!result) {
			return HttpStatus.EXPECTATION_FAILED;
		}
		return HttpStatus.OK;
	}
	
	
}
