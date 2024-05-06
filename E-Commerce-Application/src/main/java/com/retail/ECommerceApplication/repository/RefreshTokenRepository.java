package com.retail.ECommerceApplication.repository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.retail.ECommerceApplication.entity.RefreshToken;
@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Integer>{

	boolean existsByTokenAndIsBlocked(String at, boolean b);

	Optional<RefreshToken> findByToken(String refreshToken);

	List<RefreshToken> findByExpirationLessThanEqual(LocalDateTime now);

}
