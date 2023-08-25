package com.example.todo.task.repositry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.todo.task.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer>{
	List<Task> findAll();
}
