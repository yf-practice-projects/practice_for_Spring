package com.example.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.LoginUserDetails;
import com.example.dto.myPageCommentViewDto;
import com.example.entity.BulletinBoard;
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
}
