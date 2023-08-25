package com.example.todo.task.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.todo.task.service.TaskService;
import com.example.todo.task.model.Task;

@Controller
@RequestMapping("/")
public class TaskController {
	
	@Autowired
	private TaskService taskservice;
	
	@GetMapping("/")
	public String index(Model model) {
		List<Task> tasks = taskservice.findAll();
		model.addAttribute("tasks",tasks);
		return "index"; 
	}
	
	@GetMapping("/user/create/")
	public String newTask(Model model) {
		List<Task> tasks = taskservice.findAll();
		model.addAttribute("tasks",tasks);
		return "create";
	}
    @PostMapping
    public String create(@ModelAttribute Task task) {
    	taskservice.save(task);
        return "redirect:/";
    }	
}
