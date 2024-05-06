package com.retail.ECommerceApplication.responsedto;
import com.retail.ECommerceApplication.enums.UserRole;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Builder
public class UserResponseDto {
	private int userId;
	private String userName;
	private String displayName;
	private String email;
	private UserRole userRole;
	private boolean isEmailVerified;
	private boolean isDeleted;
}
