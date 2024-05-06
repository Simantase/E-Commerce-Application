package com.retail.ECommerceApplication.service;
import org.springframework.http.ResponseEntity;

import com.retail.ECommerceApplication.entity.Contact;
import com.retail.ECommerceApplication.requestdto.ContactRequest;
import com.retail.ECommerceApplication.responsedto.ContactResponse;
import com.retail.ECommerceApplication.utility.ResponseStructure;
import com.retail.ECommerceApplication.utility.SimpleResponseStructure;

import jakarta.validation.Valid;
public interface ContactService {

	ResponseEntity<ResponseStructure<ContactResponse>> addContact(@Valid ContactRequest contactRequest, int addressId);
	ResponseEntity<ResponseStructure<ContactResponse>> updateContact(@Valid ContactRequest contactRequest, int contactId);


}
