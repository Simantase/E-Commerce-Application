package com.retail.ECommerceApplication.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.retail.ECommerceApplication.entity.Address;
@Repository
public interface AddressRepository extends JpaRepository<Address,Integer>{

}
