package com.example.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.entity.BulletinBoard;
import com.example.repository.BulletinBoardRepository;


@RestController
@RequestMapping("/api/bulletin")
public class BulletinBoardRestController {
	
	@Autowired
	BulletinBoardRepository repos;
	
    @GetMapping("/hello")
    public String hello() {
        return "Hello from /api/hello";
    }
    @GetMapping("/list")
    public List<BulletinBoard> list() {
    	List<BulletinBoard> list = repos.findAll();
        return list;
    }
    
    @DeleteMapping("/delete/{id}")
    public List<BulletinBoard> delete(@PathVariable int id){
    	try {
    		repos.deleteById(id);
    	} catch (Exception e) {
			// TODO: handle exception
    		e.printStackTrace();
		}
    	
    	List<BulletinBoard> list = repos.findAll();
    	return list;
    }
}
