package com.retail.ECommerceApplication.responsedto;
import com.retail.ECommerceApplication.enums.Priority;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Builder
public class ContactResponse {
	private int contactId;
	private String name;
	private String email;
	private long contactNumber;
	private Priority priority;
}
