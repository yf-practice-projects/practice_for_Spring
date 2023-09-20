package com.example.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.LoginUserDetails;
import com.example.model.MyPageBulletinModel;
import com.example.model.MyPageCommentModel;
import com.example.service.BulletinBoardService;
import com.example.service.BulletinCommentService;


@RestController
@RequestMapping("/api/myPage")
public class MyPageRestController {
	
	@Autowired
	BulletinCommentService commentService;
	
	@Autowired
	BulletinBoardService bulletinBoardService;
	
	@GetMapping("/myBulletinList")
	public List<MyPageBulletinModel> myBulletinList(@AuthenticationPrincipal LoginUserDetails auth) {
        List<MyPageBulletinModel> list = bulletinBoardService.findMyBulletin(auth.getUsername());
        return list;
	}
	
	@GetMapping("/myCommentList")
	public List<MyPageCommentModel> myCommentList(@AuthenticationPrincipal LoginUserDetails auth) {
		List<MyPageCommentModel> list = commentService.findMyComment(auth.getUser().getUserId());
		
		return list;
	}
	
	@DeleteMapping("/deleteComment")
	public ResponseEntity<String> deleteComment(@AuthenticationPrincipal LoginUserDetails auth, @RequestParam int commentId) {
		int delCnt = commentService.deleteComment(commentId, auth.getUser().getUserId());
		
		if(delCnt == 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("non delete item");
		}
			
		return ResponseEntity.ok("success");
	}
}
