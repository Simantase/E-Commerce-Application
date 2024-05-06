package com.retail.ECommerceApplication.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.retail.ECommerceApplication.entity.Seller;
@Repository
public interface SellerRepository extends JpaRepository<Seller,Integer>{

}
