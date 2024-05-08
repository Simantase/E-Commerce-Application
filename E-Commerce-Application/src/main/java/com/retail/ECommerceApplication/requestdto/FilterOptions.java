package com.retail.ECommerceApplication.requestdto;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@Component
@ToString
public class FilterOptions {
	int minPrice;
	int maxPrice;
	String category;
	String availability;
	int rating;
	int discount;
}
