package com.retail.ECommerceApplication.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.retail.ECommerceApplication.entity.Product;
import com.retail.ECommerceApplication.entity.Seller;
import com.retail.ECommerceApplication.enums.AvailabilityStatus;
import com.retail.ECommerceApplication.enums.ProductCatagory;
import com.retail.ECommerceApplication.exception.IllegalArgumentException;
import com.retail.ECommerceApplication.repository.ProductRepository;
import com.retail.ECommerceApplication.repository.UserRepository;
import com.retail.ECommerceApplication.requestdto.FilterOptions;
import com.retail.ECommerceApplication.requestdto.ProductRequest;
import com.retail.ECommerceApplication.responsedto.ProductResponse;
import com.retail.ECommerceApplication.service.ProductService;
import com.retail.ECommerceApplication.utility.ResponseStructure;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

	private ProductRepository productRepository;
	private ResponseStructure<ProductResponse> responseStructure;
	private UserRepository userRepository;
	private ProductSpecification productSpecification;
	private ResponseStructure<List<ProductResponse>> responseStructure2;

	@Override
	public ResponseEntity<ResponseStructure<ProductResponse>> addProduct(ProductRequest productRequest) {
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		System.out.println("Name Is "+name);//it will provide username like simantasen1996
		Product product = mapToProductRequest(productRequest, new Product());
		return userRepository.findByUserName(name).map(user -> {
			Seller seller = (Seller) user;
			product.setAvailabilityStatus(AvailabilityStatus.AVAILBLE);
			Product product2 = productRepository.save(product);
			seller.getProductList().add(product2);
			userRepository.save(user);

			return ResponseEntity.ok(responseStructure.setStatusCode(HttpStatus.OK.value())
					.setStatusMessage("Product Is Saved Successfully")
					.setStatusData(mapToProductResponse(product2)));
		}).orElseThrow(() -> new IllegalArgumentException("User Name Is Not Present!!!"));

	}

	@Override
	public ResponseEntity<ResponseStructure<ProductResponse>> findProductById(int productId) {
		return productRepository.findById(productId).map(product -> {
			return ResponseEntity.ok(responseStructure.setStatusCode(HttpStatus.OK.value())
					.setStatusMessage("Product Is Fetched By Product Id")
					.setStatusData(mapToProductResponse(product)));
		}).orElseThrow(() -> new IllegalArgumentException("Product Is Not Present By Product Id!!!"));
	}

	@Override
	public ResponseEntity<ResponseStructure<ProductResponse>> updateProduct(ProductRequest productRequest,
			int productId) {
		return productRepository.findById(productId).map(product -> {
			return ResponseEntity.ok(responseStructure.setStatusCode(HttpStatus.OK.value())
					.setStatusMessage("Product Data Is Updated Successfully")
					.setStatusData(mapToProductResponse(
							productRepository.save(mapToProductRequest(productRequest, product)))));
		}).orElseThrow(() -> new IllegalArgumentException("Product Is Not Present By This Id!!!"));
	}
	@Override
	public ResponseEntity<ResponseStructure<List<ProductResponse>>> findByOptions(FilterOptions filterOptions) {
		List<Product> list = productRepository.findAll(productSpecification.buildSpecification(filterOptions));
		List<ProductResponse> prod = new ArrayList<>();
		for(Product product:list) {
			prod.add(mapToProductResponse(product));
		}
		return ResponseEntity.ok(responseStructure2.setStatusCode(HttpStatus.OK.value())
				.setStatusMessage("Product Data Is Fetched Successfuuly")
				.setStatusData(mapToProductResponse2(prod)));
	}


	// ------------------------------------------------------------------------------------------------------------------------------
	private Product mapToProductRequest(ProductRequest productRequest, Product product) {
		product.setProductName(productRequest.getProductName());
		product.setProductDescription(productRequest.getProductDescription());
		product.setProductPrice(productRequest.getProductPrice());
		product.setProductQuantity(productRequest.getProductQuantity());
		product.setAvailabilityStatus(productRequest.getAvailabilityStatus());
		product.setProductCatagory(productRequest.getProductCatagory());
		return product;
	}

	private ProductResponse mapToProductResponse(Product product) {
		return ProductResponse.builder().productId(product.getProductId()).productName(product.getProductName())
				.productDescription(product.getProductDescription()).productPrice(product.getProductPrice())
				.productQuantity(product.getProductQuantity()).availabilityStatus(product.getAvailabilityStatus())
				.productCatagory(product.getProductCatagory()).images(null).coverImage(null).build();
	}
	private List<ProductResponse> mapToProductResponse2(List<ProductResponse> prodResponses) {
	    List<ProductResponse> mappedList = new ArrayList<>();
	    
	    for (ProductResponse prod : prodResponses) {
	        ProductResponse mappedProd = ProductResponse.builder()
	                .productId(prod.getProductId())
	                .productName(prod.getProductName())
	                .productDescription(prod.getProductDescription())
	                .productPrice(prod.getProductPrice())
	                .productQuantity(prod.getProductQuantity())
	                .availabilityStatus(prod.getAvailabilityStatus())
	                .productCatagory(prod.getProductCatagory())  // Assuming it's getProductCategory() not getProductCatagory()
	                .images(null)  // Set your images here or leave as null if not applicable
	                .coverImage(null)  // Set your cover image here or leave as null if not applicable
	                .build();
	        
	        mappedList.add(mappedProd);
	    }
	    
	    return mappedList;
	}







}
