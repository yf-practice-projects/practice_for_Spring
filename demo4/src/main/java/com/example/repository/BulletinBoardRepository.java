package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.BulletinBoard;

public interface BulletinBoardRepository extends JpaRepository<BulletinBoard, Long>{
	public BulletinBoard findById(int id);
	public void deleteById(int id);
}
