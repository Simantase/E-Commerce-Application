package com.retail.ECommerceApplication.serviceimpl;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.retail.ECommerceApplication.entity.Address;
import com.retail.ECommerceApplication.entity.Contact;
import com.retail.ECommerceApplication.entity.Customer;
import com.retail.ECommerceApplication.entity.Seller;
import com.retail.ECommerceApplication.exception.IllegalArgumentException;
import com.retail.ECommerceApplication.jwt.JwtService;
import com.retail.ECommerceApplication.repository.AddressRepository;
import com.retail.ECommerceApplication.repository.UserRepository;
import com.retail.ECommerceApplication.requestdto.AddressRequest;
import com.retail.ECommerceApplication.responsedto.AddressResponse;
import com.retail.ECommerceApplication.responsedto.ContactResponse;
import com.retail.ECommerceApplication.service.AddressService;
import com.retail.ECommerceApplication.utility.ResponseStructure;
import com.retail.ECommerceApplication.utility.SimpleResponseStructure;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class AddressServiceImpl implements AddressService{

	private AddressRepository addressRepository;
	private UserRepository userRepository;
	private JwtService jwtService;
	private ResponseStructure<AddressResponse> responseStructure;
	private ResponseStructure<List<AddressResponse>> responseStructure2;

	@Override
	public ResponseEntity<ResponseStructure<AddressResponse>> addAddress(AddressRequest addressRequest,String accessToken,
			String refreshToken) {
		if (accessToken == null || refreshToken == null)
			throw new IllegalArgumentException("Failed To Add address!!!");
		String username = jwtService.getUserName(refreshToken);
		Address address = mapToAddressRequest(addressRequest, new Address());
		Address address1 = addressRepository.save(address);
		return	userRepository.findByUserName(username).map(user -> {
			if (user instanceof Seller) {
				Seller seller = (Seller) user;
				if (seller.getAddress() != null)
					return	ResponseEntity.ok(responseStructure.setStatusCode(HttpStatus.BAD_REQUEST.value())
							.setStatusMessage("Sellere address alredy filled"));
				seller.setAddress(address1);
			}
			if (user instanceof Customer) {
				Customer customer =(Customer) user;
				if (customer.getAddressList().size() > 5)
					ResponseEntity.ok(responseStructure.setStatusCode(HttpStatus.BAD_REQUEST.value())
							.setStatusMessage("Customer 5 address alredy filled"));
				customer.getAddressList().add(address1);
				addressRepository.save(address1);
			}

			userRepository.save(user);
			return	ResponseEntity.ok(responseStructure.setStatusCode(HttpStatus.OK.value())
					.setStatusMessage("Address Is Added Successfully")
					.setStatusData(mapToAddressResponse(address1)));
		}).get();

	}
	@Override
	public ResponseEntity<ResponseStructure<List<AddressResponse>>> findAddressByUser(String accessToken, String refreshToken) {
		if(accessToken==null || refreshToken==null) {
			throw new IllegalArgumentException("User Is Not Logged In!!!");
		}
		String userName = jwtService.getUserName(refreshToken);
		List<AddressResponse> addressList = new ArrayList<>();
		return	userRepository.findByUserName(userName).map(user->{
			if(user instanceof Seller) {
				Seller seller=(Seller)user;
				addressList.add(mapToAddressResponse1(seller.getAddress()));
			}
			if(user instanceof Customer) {
				Customer customer=(Customer)user;
				List<Address> address = customer.getAddressList();
				for(Address add:address) {
					addressList.add(mapToAddressResponse1(add));
				}
			}
			return	ResponseEntity.ok(responseStructure2
					.setStatusCode(HttpStatus.OK.value())
					.setStatusMessage("Address Is Fetched Successfully")
					.setStatusData(addressList));
		}).orElseThrow(()->new IllegalArgumentException("Invalid Input!!!"));
	}
	@Override
	public ResponseEntity<ResponseStructure<AddressResponse>> updateAddress(AddressRequest addressRequest,
			int addressId) {
		return	addressRepository.findById(addressId).map(adress->{
			return	ResponseEntity.ok(responseStructure.setStatusCode(HttpStatus.OK.value())
					.setStatusMessage("Address Is Updated Successfully")
					.setStatusData(mapToAddressResponse1(addressRepository.save(mapToAddressRequest(addressRequest,adress)))));
		}).orElseThrow(()-> new IllegalArgumentException("Address Is Not Updated!!!!"));
	}
	@Override
	public ResponseEntity<ResponseStructure<AddressResponse>> findAddressById(int addressId) {
		return	addressRepository.findById(addressId).map(address->{
			return	ResponseEntity.ok(responseStructure.setStatusCode(HttpStatus.OK.value())
					.setStatusMessage("Address Is Fetched Successfully By Id")
					.setStatusData(mapToAddressResponse1(address)));
		}).orElseThrow(()-> new IllegalArgumentException("Data Is Not Fetched By Id"));
	}
	//	--------------------------------------------------------------------------------------------
	private Address mapToAddressRequest(AddressRequest addressRequest,Address address) {
		address.setStreetAddress(addressRequest.getStreetAddress());
		address.setStreetAddressAdditional(addressRequest.getStreetAddressAdditional());
		address.setCity(addressRequest.getCity());
		address.setState(addressRequest.getState());
		address.setCountry(addressRequest.getCountry());
		address.setPincode(addressRequest.getPincode());
		address.setAddressType(addressRequest.getAddressType());
		return address;
	}
	private AddressResponse mapToAddressResponse(Address address) {
		return AddressResponse.builder()
				.addressId(address.getAddressId())
				.streetAddress(address.getStreetAddress())
				.streetAddressAdditional(address.getStreetAddressAdditional())
				.city(address.getCity())
				.state(address.getState())
				.country(address.getCountry())
				.pincode(address.getPincode())
				.addressType(address.getAddressType())
				.build();

	}
	private AddressResponse mapToAddressResponse1(Address address) {
		AddressResponse addressResponse = AddressResponse.builder()
				.addressId(address.getAddressId())
				.streetAddress(address.getStreetAddress())
				.streetAddressAdditional(address.getStreetAddressAdditional())
				.city(address.getCity())
				.state(address.getState())
				.country(address.getCountry())
				.pincode(address.getPincode())
				.addressType(address.getAddressType())
				.build();
		List<Contact> contactList = address.getContactList();
		if(contactList!=null) {
			addressResponse.setContactResponses(contactList.stream().map(this::mapToContactResponse).collect(Collectors.toList()));
		}
		return addressResponse;

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
