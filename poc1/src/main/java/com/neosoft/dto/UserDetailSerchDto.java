package com.neosoft.dto;

import javax.persistence.Column;

import lombok.Data;
@Data
public class UserDetailSerchDto {
	@Column(name="username",length = 10, nullable = false)
	private String username;
	@Column(name="surname",length = 10, nullable = false)
	private String surname;
	@Column(name="pincode",length = 30, nullable = false)
	private Long pincode;
}
