package com.retail.ECommerceApplication.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retail.ECommerceApplication.entity.Contact;
import com.retail.ECommerceApplication.requestdto.ContactRequest;
import com.retail.ECommerceApplication.responsedto.ContactResponse;
import com.retail.ECommerceApplication.service.ContactService;
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
@RequestMapping("/api/v1")
@AllArgsConstructor
public class ContactCotroller {
	private ContactService contactService;
	@Operation(description = "This Endpoint Is Used To Add Contact To Database",responses = {
			@ApiResponse(responseCode = "200",description = "Adding Contact Is Done Successfully"),
			@ApiResponse(responseCode = "404",description ="Invalid Operation!!!",
			content = @Content(schema = @Schema(implementation = ErrorStructure.class)))
	})
	@PostMapping("/addresses/{addressId}/contacts")
	public ResponseEntity<ResponseStructure<ContactResponse>> addContact(@RequestBody @Valid ContactRequest contactRequest,
			@PathVariable int addressId){
		return contactService.addContact(contactRequest,addressId);
	}
	@Operation(description = "This Endpoint Is Used To Update The Contact",responses = {
			@ApiResponse(responseCode = "200",description = "Update Contact Is Done Successfully"),
			@ApiResponse(responseCode = "404",description ="Invalid Operation!!!",
			content = @Content(schema = @Schema(implementation = ErrorStructure.class)))
	})
	@PutMapping("/contacts/{contactId}")
	public ResponseEntity<ResponseStructure<ContactResponse>> updateContact(@RequestBody @Valid ContactRequest contactRequest,
			@PathVariable int contactId){
		return contactService.updateContact(contactRequest,contactId);
	}
}
