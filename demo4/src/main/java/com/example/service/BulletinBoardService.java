package com.example.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.entity.BulletinBoard;
import com.example.repository.BulletinBoardRepository;


@Service
public class BulletinBoardService {

	private static final String WIN_PATH = "D:\\Programing\\practice_for_Spring\\test_folder";
	private static final String IMAGE_PATH = File.separator + "bulletinImage" + File.separator;
	private static final String MOVIE_PATH = File.separator + "bulletinMovie" + File.separator;
	private static final String UNKNOWN = File.separator + "unknown" + File.separator;
	
	
	@Autowired
	BulletinBoardRepository repos; 
	
	public boolean saveBulletin(BulletinBoard bulletinBoard, MultipartFile file) {
		try {
			bulletinBoard.setFileName(createFileName(file));
			bulletinBoard.setCreateDate(new Date());
			repos.saveAndFlush(bulletinBoard);
			saveFile(file);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			return false;
		}
		return true;
	}
	
	private String createFileName(MultipartFile file) {
//	    int dotIndex = file.getOriginalFilename().lastIndexOf('.');
//	    if (dotIndex == -1) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
	        return LocalDateTime.now().format(formatter) + "_" + file.getOriginalFilename();
//	    }
//	    return LocalTime.now().format(formatter) + "_" + file.getOriginalFilename().substring(0, dotIndex);
	}
	
	private void saveFile(MultipartFile file) {
		String fileName = createFileName(file);
		try {
			StringBuilder savePath = new StringBuilder(WIN_PATH);
		    if (fileName.endsWith(".jpg") || fileName.endsWith(".png") || fileName.endsWith(".gif")) {
		    	savePath.append(IMAGE_PATH);
		    } else if (fileName.endsWith(".mp4") || fileName.endsWith(".avi")) {
		    	savePath.append(MOVIE_PATH);
		    } else {
		    	savePath.append(UNKNOWN);
		    }
		    savePath.append(fileName);
	       
	        Path path = Paths.get(savePath.toString());
	        Files.copy(file.getInputStream(), path);
//	        byte[] bytes = file.getBytes();
//	        Files.write(path, bytes);
		} catch (Exception e) {
			e.printStackTrace();
			
		}

	}

}
