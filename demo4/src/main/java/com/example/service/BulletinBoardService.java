package com.example.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

import com.example.LoginUserDetails;
import com.example.entity.BulletinBoard;
import com.example.entity.User;
import com.example.repository.BulletinBoardRepository;

import io.micrometer.common.util.StringUtils;


@Service
public class BulletinBoardService {

	private static final String WIN_PATH = "D:\\Programing\\practice_for_Spring\\test_folder";
	private static final String IMAGE_PATH = File.separator + "bulletinImage" + File.separator;
	private static final String MOVIE_PATH = File.separator + "bulletinMovie" + File.separator;
	private static final String UNKNOWN = File.separator + "unknown" + File.separator;
	
	@Autowired
	LoginUserDetailsService loginUserDetailsService;
	
	@Autowired
	BulletinBoardRepository repos; 
	
	/**
	 * 掲示板保存
	 * @param bulletinBoard
	 * @param file
	 * @return
	 */
	public boolean saveBulletin(BulletinBoard bulletinBoard, MultipartFile file, LoginUserDetails loginUserDetails) {
		try {
			bulletinBoard.setFileName(createFileName(file));
			User user = loginUserDetails.getUser();
			bulletinBoard.setUser(user);
			bulletinBoard.setCreateDate(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
			repos.saveAndFlush(bulletinBoard);
			if(StringUtils.isNotEmpty(bulletinBoard.getFileName())){
				saveFile(file, bulletinBoard.getFileName());
			}
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			return false;
		}
		return true;
	}
	
	/**
	 * 掲示板詳細取得
	 * @param id
	 */
	public BulletinBoard findBulletin(int id) {
		BulletinBoard data = repos.findById(id);
		if(StringUtils.isNotEmpty(data.getFileName())) {
			data.setFileName(searchFile(data.getFileName()));
		}
		
		return data;
	}
	
	/**
	 * ファイル保存時名作成
	 * @param file
	 * @return
	 */
	private String createFileName(MultipartFile file) {
//	    int dotIndex = file.getOriginalFilename().lastIndexOf('.');
//	    if (dotIndex == -1) {
		if(StringUtils.isNotEmpty(file.getName())) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
	        return LocalDateTime.now().format(formatter) + "_" + file.getOriginalFilename();
		}
		return null;
//	    }
//	    return LocalTime.now().format(formatter) + "_" + file.getOriginalFilename().substring(0, dotIndex);
	}
	
	/**
	 * ファイル保存
	 * @param file
	 */
	private void saveFile(MultipartFile file, String fileName) {
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
	
	/**
	 * 掲示板で保存したファイルの取得
	 * @param fileName
	 * @return
	 */
	private String searchFile(String fileName) {
		StringBuilder originalfilePath = new StringBuilder(WIN_PATH);
		if (fileName.endsWith(".jpg") || fileName.endsWith(".png") || fileName.endsWith(".gif")) {
			originalfilePath.append(IMAGE_PATH);
			originalfilePath.append(fileName);
	    } else if (fileName.endsWith(".mp4") || fileName.endsWith(".avi")) {
	    	originalfilePath.append(MOVIE_PATH);
	    } else {
	    	originalfilePath.append(UNKNOWN);
	    }
		File fileImg = new File(originalfilePath.toString());
		byte[] byteImg = null;
		try {
			byteImg = Files.readAllBytes(fileImg.toPath());
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		if(Objects.isNull(byteImg)) {
			return "#";
		}
		String base64Data = Base64.getEncoder().encodeToString(byteImg);
		String filePath = "data:image/png;base64,"+base64Data;
		return filePath;
	}

}
