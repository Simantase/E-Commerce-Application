package com.retail.ECommerceApplication.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.retail.ECommerceApplication.entity.User;
import com.retail.ECommerceApplication.jwt.JwtService;
import com.retail.ECommerceApplication.requestdto.AuthRequestDto;
import com.retail.ECommerceApplication.requestdto.OtpRequest;
import com.retail.ECommerceApplication.requestdto.UserRequestDto;
import com.retail.ECommerceApplication.responsedto.AuthResponseDto;
import com.retail.ECommerceApplication.responsedto.UserResponseDto;
import com.retail.ECommerceApplication.service.AuthService;
import com.retail.ECommerceApplication.utility.ErrorStructure;
import com.retail.ECommerceApplication.utility.ResponseStructure;
import com.retail.ECommerceApplication.utility.SimpleResponseStructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
//@CrossOrigin(origins = "http://localhost:5173/")
public class AuthController {
	private AuthService authService;
	private JwtService jwtService;
	@Operation(description = "This EndPoint Is Used To Save The User Data",responses = {
			@ApiResponse(responseCode = "200",description = "Data Is Saved Successfully"),
			@ApiResponse(responseCode = "404",description = "Invalid Input!!!",
			content = @Content(schema = @Schema(implementation = ErrorStructure.class)))
	})
	@PostMapping("/users/register")
	public ResponseEntity<SimpleResponseStructure> registerUser(@RequestBody @Valid UserRequestDto userRequestDto){
		return authService.registerUser(userRequestDto);
	}
	@Operation(description = "This EndPoint Is Used To Verify The Email By OTP",responses = {
			@ApiResponse(responseCode = "200",description = "OTP Is Generated "),
			@ApiResponse(responseCode = "404",description = "Invalid Input!!!",
			content =@Content(schema = @Schema(implementation = ErrorStructure.class)) )
	})
	@PostMapping("/users/verifyEmail")
	public ResponseEntity<ResponseStructure<UserResponseDto>> verifyOtp(@RequestBody @Valid OtpRequest otpRequest){
		return authService.verifyOtp(otpRequest);
	}
	@GetMapping("/test")
	public String accessToken() {
		return jwtService.generateAccessToken(new User(),"SELLER"); 
	}
	@Operation(description = "This EndPoint Is Used To Login Into The Application",responses = {
			@ApiResponse(responseCode = "200",description = "Login Is Successful"),
			@ApiResponse(responseCode = "404",description = "Invalid Operation!!!",
			content =@Content(schema = @Schema(implementation = ErrorStructure.class)) )
	})
	@PostMapping("/users/login")
	public ResponseEntity<ResponseStructure<AuthResponseDto>> userLogin(@RequestBody @Valid AuthRequestDto authRequestDto,@CookieValue(name="at",required = false) String accessToken,
			@CookieValue(name = "rt",required = false) String refreshToken){
		return authService.userLogin(authRequestDto,accessToken,refreshToken);
	}
	@Operation(description = "This EndPoint Is Used To Logout From Application",responses = {
			@ApiResponse(responseCode = "200",description = "Logout Is Done Successfully"),
			@ApiResponse(responseCode ="404",description = "Invalid Operation!!!",
			content =@Content(schema = @Schema(implementation = ErrorStructure.class)) )
	})
	@PostMapping("/users/logout")
	public ResponseEntity<SimpleResponseStructure> logout(@CookieValue(name = "at", required = false) String accessToken,
			@CookieValue(name = "rt", required = false) String refreshToken) {
		return authService.logout(refreshToken, accessToken);
	}
	@Operation(description = "This Endpoint Is Used To Re-Generate Access Token",responses = {
			@ApiResponse(responseCode = "200",description = "Re-Generate Access Token Is Done Successfully"),
			@ApiResponse(responseCode = "404",description ="Invalid Operation!!!",
			content = @Content(schema = @Schema(implementation = ErrorStructure.class)))
	})
	@PostMapping("/users/refreshLoginAndTokenRotation")
	public ResponseEntity<ResponseStructure<AuthResponseDto>> refreshLogin(@CookieValue(name="at",required = false) String accessToken,
			@CookieValue(name = "rt",required = false) String refreshToken){
		return authService.refreshLogin(accessToken,refreshToken);
	}
}