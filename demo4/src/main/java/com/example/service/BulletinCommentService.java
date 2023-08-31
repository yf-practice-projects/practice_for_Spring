package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.BulletinComment;
import com.example.repository.BulletinCommentRepository;

@Service
public class BulletinCommentService {
	
	@Autowired
	BulletinCommentRepository commentRepo;
	
	public List<BulletinComment> findComment(int bulletinId) {
		List<BulletinComment> list = commentRepo.findByBulletinBoard_id(bulletinId);
		
		return list;
	}
	
	
	public boolean addComment(BulletinComment comment) {
		commentRepo.saveAndFlush(comment);
		return true;
	}
	
	public boolean deleteComment(){
		commentRepo.deleteAllById(null);
		return true;
	}
}
