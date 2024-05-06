package com.retail.ECommerceApplication.utility;

import java.time.LocalDateTime;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.retail.ECommerceApplication.entity.AccessToken;
import com.retail.ECommerceApplication.entity.RefreshToken;
import com.retail.ECommerceApplication.repository.AccessTokenRepository;
import com.retail.ECommerceApplication.repository.RefreshTokenRepository;

import lombok.AllArgsConstructor;
import java.util.*;
@Component
@AllArgsConstructor
public class SchedulerJob {
	private AccessTokenRepository accessTokenRepository;
	private RefreshTokenRepository refreshTokenRepository;

	@Scheduled(fixedDelay =  1000l*60*60)
	public void deleting() {
		List<AccessToken> at =accessTokenRepository.findByExpirationLessThanEqual(LocalDateTime.now());
		accessTokenRepository.deleteAll(at);
		List<RefreshToken> rt=refreshTokenRepository.findByExpirationLessThanEqual(LocalDateTime.now());
		refreshTokenRepository.deleteAll(rt);
	}
}
