package com.retail.ECommerceApplication.controller;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.retail.ECommerceApplication.service.ImageService;
import com.retail.ECommerceApplication.utility.ResponseStructure;
import com.retail.ECommerceApplication.utility.SimpleResponseStructure;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/api/v1")
public class ImageController {
	private ImageService imageService;

	@PostMapping("/products/{productId}/imageType/{imageType}/images")
	public ResponseEntity<SimpleResponseStructure> addImage(@PathVariable int productId,@PathVariable String imageType,
			MultipartFile image) throws IOException{
		return imageService.addImage(productId,imageType,image);
	}
	@GetMapping("/images/{imageId}")
	public ResponseEntity<byte[]> getImage(@PathVariable String imageId){
		return imageService.getImage(imageId);
	}

}
