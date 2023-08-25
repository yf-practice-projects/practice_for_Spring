package com.example;

import org.springframework.security.core.authority.AuthorityUtils;

import com.example.entity.User;

public class LoginUserDetails extends org.springframework.security.core.userdetails.User{
	private static final long serialVersionUID = 1L;
    private final User user;

    public LoginUserDetails(User user) {
        super(user.getUserId(), user.getEncodedPassword(), 
              AuthorityUtils.createAuthorityList("ROLE_USER"));
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
