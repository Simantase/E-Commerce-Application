package com.retail.ECommerceApplication.service;
import org.springframework.http.ResponseEntity;

import com.retail.ECommerceApplication.requestdto.AuthRequestDto;
import com.retail.ECommerceApplication.requestdto.OtpRequest;
import com.retail.ECommerceApplication.requestdto.UserRequestDto;
import com.retail.ECommerceApplication.responsedto.AuthResponseDto;
import com.retail.ECommerceApplication.responsedto.UserResponseDto;
import com.retail.ECommerceApplication.utility.ResponseStructure;
import com.retail.ECommerceApplication.utility.SimpleResponseStructure;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
public interface AuthService {

	ResponseEntity<SimpleResponseStructure> registerUser(@Valid UserRequestDto userRequestDto);
	ResponseEntity<ResponseStructure<UserResponseDto>> verifyOtp(OtpRequest otpRequest);
	ResponseEntity<ResponseStructure<AuthResponseDto>> userLogin(@Valid AuthRequestDto authRequestDto,String accessToken, String refreshToken);
	ResponseEntity<SimpleResponseStructure> logout(String refreshToken, String accessToken);
	ResponseEntity<ResponseStructure<AuthResponseDto>> refreshLogin(String accessToken, String refreshToken);



}
