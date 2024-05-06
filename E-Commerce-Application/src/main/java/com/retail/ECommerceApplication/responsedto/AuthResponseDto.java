package com.retail.ECommerceApplication.responsedto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.retail.ECommerceApplication.enums.UserRole;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Builder
public class AuthResponseDto {
	private int userId;
	private String userName;
	private UserRole userRole;
	private long accessExpiration;
	private long refreshExpiration;

	private LocalDateTime accessExpiry;
	private LocalDateTime refreshExpiry;
}
