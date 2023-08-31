package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.BulletinComment;
import java.util.List;




public interface BulletinCommentRepository extends JpaRepository<BulletinComment, Long>{
	public List<BulletinComment> findByBulletinBoard_id(int id);
	
	
}
