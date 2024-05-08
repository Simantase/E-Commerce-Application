package com.retail.ECommerceApplication.service;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.retail.ECommerceApplication.requestdto.FilterOptions;
import com.retail.ECommerceApplication.requestdto.ProductRequest;
import com.retail.ECommerceApplication.responsedto.ProductResponse;
import com.retail.ECommerceApplication.utility.ResponseStructure;
public interface ProductService {
	ResponseEntity<ResponseStructure<ProductResponse>> addProduct(ProductRequest productRequest);
	ResponseEntity<ResponseStructure<ProductResponse>> findProductById(int productId);
	ResponseEntity<ResponseStructure<ProductResponse>> updateProduct(ProductRequest productRequest, int productId);
	ResponseEntity<ResponseStructure<List<ProductResponse>>> findByOptions(FilterOptions filterOptions);
	ResponseEntity<ResponseStructure<List<ProductResponse>>> findAll();
	

}
