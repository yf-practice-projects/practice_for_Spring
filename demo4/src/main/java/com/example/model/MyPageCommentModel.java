package com.example.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class MyPageCommentModel {
	private int Id;
	private String comment;
	private int bulletinId;
	private String bulletinTitle;
	private LocalDateTime createDate;
}
