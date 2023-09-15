package com.example.dto;

import java.time.LocalDateTime;

public interface myPageCommentViewDto {
	int getId();
	String getComment();
	int getBulletinBoard_id();
	String getBulletinBoard_title();
	LocalDateTime getCreateDate();
}
