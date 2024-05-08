package com.retail.ECommerceApplication.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.retail.ECommerceApplication.entity.Image;
import com.retail.ECommerceApplication.entity.Product;
import com.retail.ECommerceApplication.enums.ImageType;
public interface ImageRepository extends MongoRepository<Image,String>{

	Optional<Image> findByProductIdAndImageType(int productId, ImageType cover);
	Optional<Product> findByProductId(int productId);
	List<Image> findByImageId(int imageId);

}
