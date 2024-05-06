package com.retail.ECommerceApplication.requestdto;
import com.retail.ECommerceApplication.enums.Priority;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ContactRequest {
	@Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Name Must Contain Only Alphabetic Characters")
	private String name;
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@gmail\\.com$", message = "Email must end with @gmail.com")
	private String email;
	@Digits(integer = 10, fraction = 0, message = "Contact Number Should Only Contain Numeric Data")
	private long contactNumber;
	private Priority priority;
}
