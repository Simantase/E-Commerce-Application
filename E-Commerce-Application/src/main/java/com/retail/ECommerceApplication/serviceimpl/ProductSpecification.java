package com.retail.ECommerceApplication.serviceimpl;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.retail.ECommerceApplication.entity.Product;
import com.retail.ECommerceApplication.requestdto.FilterOptions;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@Component
public class ProductSpecification{
	private FilterOptions filterOptions;
	public Specification<Product> buildSpecification(FilterOptions filterOptions){
		return (root,query,criteriaBuilder)->{
			List<Predicate> predicate=new ArrayList<Predicate>();
			if(filterOptions.getMinPrice()>0) {
				predicate.add(criteriaBuilder.greaterThanOrEqualTo(root.get("productPrice"), filterOptions.getMinPrice()));
			}
			if(filterOptions.getMaxPrice()>0) {
				predicate.add(criteriaBuilder.lessThanOrEqualTo(root.get("productPrice"),filterOptions.getMaxPrice()));
			}
			if(filterOptions.getCategory()!=null) {
				predicate.add(criteriaBuilder.equal(root.get("productCatagory"),filterOptions.getCategory()));
			}
			if(filterOptions.getAvailability()!=null) {
				predicate.add(criteriaBuilder.equal(root.get("availabilityStatus"),filterOptions.getAvailability()));
			}
			return criteriaBuilder.and(predicate.toArray(new Predicate[0]));

		};
	}

}
