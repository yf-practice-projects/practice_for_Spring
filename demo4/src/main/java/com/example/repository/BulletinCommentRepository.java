package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.dto.myPageCommentViewDto;
import com.example.entity.BulletinComment;
import java.util.List;




public interface BulletinCommentRepository extends JpaRepository<BulletinComment, Long>{
	public List<BulletinComment> findByBulletinBoard_id(int id);
	public List<myPageCommentViewDto> findByUser_userId(String userId);
	
	@Modifying
	@Transactional
	@Query("UPDATE BulletinComment comment SET comment.deleteFlag = true WHERE comment.id = ?1 AND comment.user.userId = ?2")
	public int deleteByCommentIdAndUserId(int commentId, String userId);
	
	
}
