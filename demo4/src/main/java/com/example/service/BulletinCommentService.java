package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.myPageCommentViewDto;
import com.example.entity.BulletinComment;
import com.example.model.MyPageCommentModel;
import com.example.repository.BulletinCommentRepository;

@Service
public class BulletinCommentService {
	
	@Autowired
	BulletinCommentRepository commentRepo;
	
	public List<BulletinComment> findComment(int bulletinId) {
		List<BulletinComment> list = commentRepo.findByBulletinBoard_id(bulletinId);
		
		return list;
	}
	
	/**
	 * 自分のコメントリスト
	 * @param userId
	 * @return
	 */
	public List<MyPageCommentModel> findMyComment(String userId) {
		List<myPageCommentViewDto> list = commentRepo.findByUser_userId(userId);
		List<MyPageCommentModel> modelList = new ArrayList<MyPageCommentModel>();
		list.forEach(dto -> {
			modelList.add(convertDtoToModel(dto));
		});
		return modelList;
	}
	
	private MyPageCommentModel convertDtoToModel(myPageCommentViewDto dto) {
		MyPageCommentModel model = new MyPageCommentModel();
		model.setId(dto.getId());
		model.setComment(dto.getComment());
		model.setBulletinId(dto.getBulletinBoard_id());
		model.setBulletinTitle(dto.getBulletinBoard_title());
		model.setCreateDate(dto.getCreateDate());
		return model;
	}
	
	
	
	
	/**
	 * コメント登録
	 * @param comment
	 * @return
	 */
	public boolean addComment(BulletinComment comment) {
		try {
			commentRepo.saveAndFlush(comment);
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		
		return true;
	}
	
	/**
	 * コメント削除
	 * 使わないかも
	 * @return
	 */
	public boolean deleteComment(){
		commentRepo.deleteAllById(null);
		return true;
	}
}
