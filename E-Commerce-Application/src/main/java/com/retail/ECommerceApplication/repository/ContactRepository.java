package com.retail.ECommerceApplication.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.retail.ECommerceApplication.entity.Contact;
@Repository
public interface ContactRepository extends JpaRepository<Contact,Integer>{

}
