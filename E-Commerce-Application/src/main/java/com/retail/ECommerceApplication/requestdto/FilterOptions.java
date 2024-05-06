package com.retail.ECommerceApplication.requestdto;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class FilterOptions {
	int minPrice;
	int maxPrice;
	String category;
	String availability;
	int rating;
	int discount;
	@Override
	public String toString() {
		return "FilterOptions [minPrice=" + minPrice + ", maxPrice=" + maxPrice + ", category=" + category
				+ ", availability=" + availability + ", rating=" + rating + ", discount=" + discount + "]";
	}
	
}
