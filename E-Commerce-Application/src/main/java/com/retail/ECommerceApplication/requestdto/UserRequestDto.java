package com.retail.ECommerceApplication.requestdto;
import com.retail.ECommerceApplication.enums.UserRole;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class UserRequestDto {
	private String name;
	private String email;
	private String password;
	private UserRole userRole;
	
}
