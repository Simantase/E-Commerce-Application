package com.retail.ECommerceApplication.responsedto;
import java.util.ArrayList;
import java.util.List;

import com.retail.ECommerceApplication.enums.AddressType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Builder
public class AddressResponse {
	private int addressId;
	private String streetAddress;
	private String streetAddressAdditional;
	private String city;
	private String state;
	private String country;
	private int pincode;
	private AddressType addressType;
	private List<ContactResponse> contactResponses=new ArrayList<ContactResponse>();
}
