package com.retail.ECommerceApplication.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import com.retail.ECommerceApplication.entity.Product;

public interface ProductRepository extends JpaRepository<Product,Integer>,JpaSpecificationExecutor<Product>{


}
