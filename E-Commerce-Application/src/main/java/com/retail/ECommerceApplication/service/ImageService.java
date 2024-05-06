package com.retail.ECommerceApplication.service;
import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import com.retail.ECommerceApplication.utility.ResponseStructure;
import com.retail.ECommerceApplication.utility.SimpleResponseStructure;
public interface ImageService {

	ResponseEntity<SimpleResponseStructure> addImage(int productId, String imageType, MultipartFile image) throws IOException;

	ResponseEntity<byte[]> getImage(String imageId);

	

}
