package com.retail.ECommerceApplication.entity;
import com.retail.ECommerceApplication.enums.AvailabilityStatus;
import com.retail.ECommerceApplication.enums.ProductCatagory;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productId;
	private String productName;
	private String productDescription;
	private double productPrice;
	private int productQuantity;
	private AvailabilityStatus availabilityStatus;
	private ProductCatagory productCatagory;
}
