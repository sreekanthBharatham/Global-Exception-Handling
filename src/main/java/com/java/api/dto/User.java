package com.java.api.dto;


import com.java.api.common.UserStatus;
import lombok.Data;

@Data
public class User {
	private String username;
    private UserStatus userStatus;
    private String airline;

}
