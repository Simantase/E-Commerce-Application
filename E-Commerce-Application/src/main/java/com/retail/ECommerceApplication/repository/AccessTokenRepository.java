package com.retail.ECommerceApplication.repository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.retail.ECommerceApplication.entity.AccessToken;
@Repository
public interface AccessTokenRepository extends JpaRepository<AccessToken,Integer>{

	boolean existsByTokenAndIsBlocked(String at, boolean b);

	Optional<AccessToken> findByToken(String accessToken);

	List<AccessToken> findByExpirationLessThanEqual(LocalDateTime now);

}
