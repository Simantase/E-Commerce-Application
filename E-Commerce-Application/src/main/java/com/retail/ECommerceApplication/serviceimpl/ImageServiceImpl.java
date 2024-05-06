package com.retail.ECommerceApplication.serviceimpl;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.retail.ECommerceApplication.entity.Image;
import com.retail.ECommerceApplication.enums.ImageType;
import com.retail.ECommerceApplication.exception.IllegalArgumentException;
import com.retail.ECommerceApplication.repository.ImageRepository;
import com.retail.ECommerceApplication.repository.ProductRepository;
import com.retail.ECommerceApplication.service.ImageService;
import com.retail.ECommerceApplication.utility.ResponseStructure;
import com.retail.ECommerceApplication.utility.SimpleResponseStructure;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class ImageServiceImpl implements ImageService{

	private ImageRepository imageRepository;
	private ProductRepository productRepository;
	private SimpleResponseStructure simpleResponseStructure;

	@Override
	public ResponseEntity<SimpleResponseStructure> addImage(int productId, String imageType, MultipartFile image) throws IOException {
		if (!productRepository.existsById(productId)) {
			throw new IllegalArgumentException("Product With This Given Id Is Not Present!!!");
		}
		imageRepository.findByProductIdAndImageType(productId,ImageType.COVER).ifPresent(image1->{
			image1.setImageType(ImageType.OTHERS);
			imageRepository.save(image1);
		});
		try {
			Image image2=new Image();
			image2.setProductId(productId);
			image2.setImageType(ImageType.valueOf(imageType.toUpperCase()));
			System.out.println("Image:"+image);
			image2.setImageBytes(image.getBytes());
			image2.setContentType(image.getContentType());
			imageRepository.save(image2);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(simpleResponseStructure.setStatus(HttpStatus.OK.value())
				.setMessage("Image Is Saved Successfully"));
	}

	@Override
	public ResponseEntity<byte[]> getImage(String imageId) {
		return imageRepository.findById(imageId).map(image->{
			return	ResponseEntity.ok()
					.contentLength(image.getImageBytes().length)
					.contentType(MediaType.valueOf(image.getContentType()))
					.body(image.getImageBytes());
		}).orElseThrow(()-> new IllegalArgumentException("Image Is Not Found!!!"));
	}



}
