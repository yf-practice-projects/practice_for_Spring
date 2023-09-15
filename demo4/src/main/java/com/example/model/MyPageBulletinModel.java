package com.example.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class MyPageBulletinModel {
	private int id;
	private String title;
	private String content;
	private LocalDateTime createDate;
}
