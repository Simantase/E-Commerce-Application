package com.retail.ECommerceApplication.service;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.retail.ECommerceApplication.entity.Address;
import com.retail.ECommerceApplication.requestdto.AddressRequest;
import com.retail.ECommerceApplication.responsedto.AddressResponse;
import com.retail.ECommerceApplication.utility.ResponseStructure;
import com.retail.ECommerceApplication.utility.SimpleResponseStructure;

import jakarta.validation.Valid;
public interface AddressService {
	ResponseEntity<ResponseStructure<AddressResponse>> addAddress(AddressRequest addressRequest,String accessToken, String refreshToken);
	ResponseEntity<ResponseStructure<List<AddressResponse>>> findAddressByUser(String accessToken, String refreshToken);
	ResponseEntity<ResponseStructure<AddressResponse>> updateAddress(AddressRequest addressRequest, int addressId);
	ResponseEntity<ResponseStructure<AddressResponse>> findAddressById(int addressId);

}
