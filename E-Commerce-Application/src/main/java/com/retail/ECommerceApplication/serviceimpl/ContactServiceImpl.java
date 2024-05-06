package com.retail.ECommerceApplication.serviceimpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.retail.ECommerceApplication.entity.Contact;
import com.retail.ECommerceApplication.exception.IllegalArgumentException;
import com.retail.ECommerceApplication.repository.AddressRepository;
import com.retail.ECommerceApplication.repository.ContactRepository;
import com.retail.ECommerceApplication.requestdto.ContactRequest;
import com.retail.ECommerceApplication.responsedto.ContactResponse;
import com.retail.ECommerceApplication.service.ContactService;
import com.retail.ECommerceApplication.utility.ResponseStructure;
import com.retail.ECommerceApplication.utility.SimpleResponseStructure;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class ContactServiceImpl implements ContactService{
	private ContactRepository contactRepository;
	private AddressRepository addressRepository;
	private ResponseStructure<ContactResponse> responseStructure;
	@Override
	public ResponseEntity<ResponseStructure<ContactResponse>> addContact(@Valid ContactRequest contactRequest,int addressId) {
		Contact contact = mapToContactRequest(contactRequest,new Contact());
		return	addressRepository.findById(addressId).map(address->{
			if(address.getContactList().size()>2) {
				return	ResponseEntity.ok(responseStructure
						.setStatusCode(HttpStatus.BAD_REQUEST.value())
						.setStatusMessage("Two Addresses Can Not Be Saved"));
			}
			Contact saveContact = contactRepository.save(contact);
			address.getContactList().add(saveContact);
			addressRepository.save(address);
			return ResponseEntity.ok(responseStructure.setStatusCode(HttpStatus.OK.value())
					.setStatusMessage("Contact Is Added Successfully")
					.setStatusData(mapToContactResponse(contact)));
		}).orElseThrow(()->new IllegalArgumentException("Address Id Is Not Find!!!"));
	}
	@Override
	public ResponseEntity<ResponseStructure<ContactResponse>> updateContact(@Valid ContactRequest contactRequest,
			int contactId) {
		return	contactRepository.findById(contactId).map(contact->{
			return	ResponseEntity.ok(responseStructure.setStatusCode(HttpStatus.OK.value())
					.setStatusMessage("Contact Data Is Updated Successfully")
					.setStatusData(mapToContactResponse(contactRepository.save(mapToContactRequest(contactRequest,contact)))));
		}).orElseThrow(()->new IllegalArgumentException("Contact Data Is Not Updated"));
	}
	//--------------------------------------------------------------------------------------------------
	private Contact mapToContactRequest(ContactRequest contactRequest,Contact contact) {
		contact.setName(contactRequest.getName());
		contact.setEmail(contactRequest.getEmail());
		contact.setContactNumber(contactRequest.getContactNumber());
		contact.setPriority(contactRequest.getPriority());
		return contact;
	}
	private ContactResponse mapToContactResponse(Contact contact) {
		return ContactResponse.builder()
				.contactId(contact.getContactId())
				.name(contact.getName())
				.email(contact.getEmail())
				.contactNumber(contact.getContactNumber())
				.priority(contact.getPriority())
				.build();
	}


}
