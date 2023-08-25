package com.example.todo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
	
	public User findById(int id);
	public void deleteById(int id);
	
}