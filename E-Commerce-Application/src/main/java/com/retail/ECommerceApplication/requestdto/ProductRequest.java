package com.retail.ECommerceApplication.requestdto;
import com.retail.ECommerceApplication.enums.AvailabilityStatus;
import com.retail.ECommerceApplication.enums.ProductCatagory;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ProductRequest {
	@NotNull(message = "Product Name Should Not Be Null")
	@Pattern(regexp = "^[a-zA-Z0-9\\s]*$", message = "Address Must Contain Only Alphabetic Characters")
	private String productName;
	@Pattern(regexp = "^[a-zA-Z0-9\\s]*$", message = "Product Description Must Contain Only Alphabetic Characters")
	@Size(max = 100, message = "Product Description Must Not Exceed 500 Characters")
	private String productDescription;
	@Min(value = 10,message = "Product Value Is Not Exceed The Lower Limit Of Product Price")
	@Max(value = 20000,message = "Product Value Is Exceed The Upper Limit Of Product Price")
	private double productPrice;
	@Max(value =20,message = "Product Quantity Is Exceed The Upper Limit Of Product Quantity")
	private int productQuantity;
	private AvailabilityStatus availabilityStatus;
	private ProductCatagory productCatagory;
}
