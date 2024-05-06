package com.retail.ECommerceApplication.requestdto;
import com.retail.ECommerceApplication.enums.AddressType;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class AddressRequest {
	//@Pattern(regexp = "^[a-zA-Z0-9\\s]*$", message = "Street Address Must Be Alphanumeric")
	private String streetAddress;
	//@Pattern(regexp = "^[a-zA-Z0-9\\s]*$", message = "Additional Street Address Must Be Alphanumeric")
	private String streetAddressAdditional;
	//@Pattern(regexp = "^[a-zA-Z]+$", message = "City Must Contain Only Alphabetic Characters")
	private String city;
	//@Pattern(regexp = "^[a-zA-Z\\s]+$", message = "State Must Contain Only Alphabetic Characters")
	private String state;
	//@Pattern(regexp = "^[a-zA-Z]+$", message = "Country Must Contain Only Alphabetic Characters")
	private String country;
	//@Digits(integer = 6, fraction = 0, message = "Pincode Should Only Be Numeric With A Maximum Of 6 Digits")
	private int pincode;
	private AddressType addressType;
}
