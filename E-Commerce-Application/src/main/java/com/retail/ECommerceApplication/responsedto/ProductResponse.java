package com.retail.ECommerceApplication.responsedto;
import java.util.List;
import com.retail.ECommerceApplication.enums.AvailabilityStatus;
import com.retail.ECommerceApplication.enums.ProductCatagory;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Builder
public class ProductResponse {
	private int productId;
	private String productName;
	private String productDescription;
	private double productPrice;
	private int productQuantity;
	private AvailabilityStatus availabilityStatus;
	private ProductCatagory productCatagory;
	private List<String> images;
	private String coverImage;
}
