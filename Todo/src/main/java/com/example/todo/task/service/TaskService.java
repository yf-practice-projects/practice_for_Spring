package com.example.todo.task.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todo.task.model.Task;
import com.example.todo.task.repositry.TaskRepository;

@Service
public class TaskService {
	
	@Autowired
	private TaskRepository taskRepository;
	
	public List<Task> findAll() {
		return taskRepository.findAll();
	}
	
	public Task save(Task task) {
		return taskRepository.save(task);
	}
	
    public Optional<Task> fnidOne(Integer id) {
        return taskRepository.findById(id);
    }
//    
//    public void delete(Long id) {
//    	taskRepository.delete(id);
//    }
}
