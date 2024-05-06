package com.retail.ECommerceApplication.entity;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import com.google.common.net.MediaType;
import com.retail.ECommerceApplication.enums.ImageType;
import lombok.Getter;
import lombok.Setter;
@Document(collection = "images")//For Mongodb Only
@Getter
@Setter
public class Image {
	@MongoId
	private String imageId;
	private ImageType imageType;
	private byte[] imageBytes;
	private int productId;
	private String contentType;
}
