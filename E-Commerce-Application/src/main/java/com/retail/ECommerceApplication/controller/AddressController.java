package com.retail.ECommerceApplication.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retail.ECommerceApplication.entity.Address;
import com.retail.ECommerceApplication.requestdto.AddressRequest;
import com.retail.ECommerceApplication.responsedto.AddressResponse;
import com.retail.ECommerceApplication.service.AddressService;
import com.retail.ECommerceApplication.utility.ErrorStructure;
import com.retail.ECommerceApplication.utility.ResponseStructure;
import com.retail.ECommerceApplication.utility.SimpleResponseStructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class AddressController {
	private AddressService addressService;
	@Operation(description = "This Endpoint Is Used To Add Address In Database",responses = {
			@ApiResponse(responseCode = "200",description = "Adding Address Is Done Successfully"),
			@ApiResponse(responseCode = "404",description ="Invalid Operation!!!",
			content = @Content(schema = @Schema(implementation = ErrorStructure.class)))
	})
	@PostMapping("/users/addresses")
	private  ResponseEntity<ResponseStructure<AddressResponse>> addAddress(@RequestBody @Valid AddressRequest addressRequest,
			@CookieValue(name="at",required = false)String accessToken,@CookieValue(name="rt",required = false) String refreshToken) {
		return addressService.addAddress(addressRequest, accessToken, refreshToken);
	}
	@Operation(description = "This Endpoint Is Used To Find The Address By User",responses = {
			@ApiResponse(responseCode = "200",description = "Find The Address By User Is Done Successfully"),
			@ApiResponse(responseCode = "404",description ="Invalid Operation!!!",
			content = @Content(schema = @Schema(implementation = ErrorStructure.class)))
	})
	@GetMapping("/users/addresses")
	private ResponseEntity<ResponseStructure<List<AddressResponse>>> findAddressByUser(@CookieValue(name="at",required = false) String accessToken,
			@CookieValue(name="rt",required = false)String refreshToken){
		return addressService.findAddressByUser(accessToken,refreshToken);
	}
	@Operation(description = "This Endpoint Is Used To Update The Address",responses = {
			@ApiResponse(responseCode = "200",description = "Update The Address Is Done Successfully"),
			@ApiResponse(responseCode = "404",description ="Invalid Operation!!!",
			content = @Content(schema = @Schema(implementation = ErrorStructure.class)))
	})
	@PutMapping("/addresses/{addressId}")
	private ResponseEntity<ResponseStructure<AddressResponse>> updateAddress(@RequestBody AddressRequest addressRequest,
			@PathVariable int addressId){
		return addressService.updateAddress(addressRequest,addressId);
	}
	@GetMapping("/addresses/{addressId}")
	private ResponseEntity<ResponseStructure<AddressResponse>> findAddressById(@PathVariable int addressId){
		return addressService.findAddressById(addressId);
	}
}
