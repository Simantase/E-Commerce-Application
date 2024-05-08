package com.retail.ECommerceApplication.controller;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.retail.ECommerceApplication.entity.Product;
import com.retail.ECommerceApplication.requestdto.FilterOptions;
import com.retail.ECommerceApplication.requestdto.ProductRequest;
import com.retail.ECommerceApplication.responsedto.ProductResponse;
import com.retail.ECommerceApplication.service.ProductService;
import com.retail.ECommerceApplication.serviceimpl.ProductSpecification;
import com.retail.ECommerceApplication.utility.ErrorStructure;
import com.retail.ECommerceApplication.utility.ResponseStructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class ProductController {

	private ProductService productService;
	private ProductSpecification productSpecification;

	@Operation(description = "This EndPoint Is Used To Add The Product In Database", responses = {
			@ApiResponse(responseCode = "200", description = "Product Is Saved Successfully"),
			@ApiResponse(responseCode = "404", description = "Invalid Operation!!!!", content = @Content(schema = @Schema(implementation = ErrorStructure.class))) })
	@PreAuthorize("hasAuthority('SELLER')")
	@PostMapping("/products")
	public ResponseEntity<ResponseStructure<ProductResponse>> addProduct(@RequestBody ProductRequest productRequest) {
		return this.productService.addProduct(productRequest);
	}

	@Operation(description = "This EndPoint Is Used To Fetch The Product Based On Id", responses = {
			@ApiResponse(responseCode = "200", description = "Product Is Fetched Successfully"),
			@ApiResponse(responseCode = "404", description = "Invalid Operation!!!!", content = @Content(schema = @Schema(implementation = ErrorStructure.class))) })
	@PreAuthorize("hasAuthority('SELLER') OR hasAuthority('CUSTOMER')")
	@GetMapping("/products/{productId}")
	public ResponseEntity<ResponseStructure<ProductResponse>> findProductById(@PathVariable int productId) {
		return productService.findProductById(productId);
	}

	@Operation(description = "This EndPoint Is Used To Fetch The Product Based On Id", responses = {
			@ApiResponse(responseCode = "200", description = "Product Is Fetched Successfully"),
			@ApiResponse(responseCode = "404", description = "Invalid Operation!!!!", content = @Content(schema = @Schema(implementation = ErrorStructure.class))) })
	@PreAuthorize("hasAuthority('SELLER')")
	@PutMapping("/products/{productId}")
	public ResponseEntity<ResponseStructure<ProductResponse>> updateProduct(@RequestBody ProductRequest productRequest,
			@PathVariable int productId) {
		return productService.updateProduct(productRequest, productId);
	}
	@GetMapping("/products")
	public  String fetchProduct(@RequestParam(required = false)Integer minPrice,@RequestParam(required = false) Integer maxPrice,
			@RequestParam(required = false)String availbility,@RequestParam (required = false)Integer rating,@RequestParam(required = false)Integer discount,
			@RequestParam(required = false)String catagory){
		System.out.println("Max Price:"+maxPrice+"Min Price"+minPrice);
		return "Success";
	}
	@GetMapping("/productsfilter")
	public String fetchProductFilter(FilterOptions options) {
		System.out.println(options);
		return "Success";
	}
	@GetMapping("/productsFilter")
	public Specification<Product> productFiler(FilterOptions filterOptions) {
		System.out.println(filterOptions);
		System.out.println(productSpecification.buildSpecification(filterOptions));
		return null;
	}
	@GetMapping("/productsByOptions")
	public ResponseEntity<ResponseStructure<List<ProductResponse>>> findByOptions(FilterOptions filterOptions) {
		return productService.findByOptions(filterOptions);
	}
	@GetMapping("/products/allProducts")
	public ResponseEntity<ResponseStructure<List<ProductResponse>>> findAll(){
		return productService.findAll();
	}

}
