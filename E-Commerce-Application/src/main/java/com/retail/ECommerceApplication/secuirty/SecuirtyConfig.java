package com.retail.ECommerceApplication.secuirty;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.retail.ECommerceApplication.entity.Customer;
import com.retail.ECommerceApplication.jwt.JwtFilter;
import com.retail.ECommerceApplication.jwt.JwtService;
import com.retail.ECommerceApplication.jwt.RefreshFilter;
import com.retail.ECommerceApplication.repository.AccessTokenRepository;
import com.retail.ECommerceApplication.repository.RefreshTokenRepository;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@EnableMethodSecurity
public class SecuirtyConfig {
	private CustomUserDetailsService userDetailsService;
	private AccessTokenRepository accessTokenRepository;
	private RefreshTokenRepository refreshTokenRepository;
	private JwtService jwtService;
	@Bean
	AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder());
		provider.setUserDetailsService(userDetailsService);
		return provider;
	}
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10);
	}
	//	@Bean
	//	SecurityFilterChain secuirtyFilterChain(HttpSecurity httpSecurity) throws Exception{
	//		return httpSecurity.csrf(csrf->csrf.disable())
	//				.authorizeHttpRequests(auth->auth
	//						.requestMatchers("/api/v1/users/login","/api/v1/users/register","/api/v1/users/verifyEmail"
	//								,"api/v1/users/logout")
	//						.permitAll().anyRequest().authenticated())
	//				.sessionManagement(management->management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	//				.authenticationProvider(authenticationProvider())
	//				.addFilterBefore(new JwtFilter(accessTokenRepository, refreshTokenRepository, jwtService),UsernamePasswordAuthenticationFilter.class)
	//				.build();
	//	}
	@Order(1)
	@Bean
	SecurityFilterChain secuirtyFilterChain(HttpSecurity httpSecurity) throws Exception{
		return httpSecurity.csrf(csrf->csrf.disable())
				//	.securityMatchers(matchers->matchers.requestMatchers("/api/v1/users/login","/api/v1/users/register","/api/v1/users/verifyEmail","api/v1/users/logout")
				.authorizeHttpRequests(auth -> auth.requestMatchers("/api/v1/users/login","/api/v1/users/register","/api/v1/users/verifyEmail","/api/v1/users/logout").permitAll().anyRequest().authenticated())
				.sessionManagement(management->management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authenticationProvider(authenticationProvider())
				.addFilterBefore(new JwtFilter(accessTokenRepository, refreshTokenRepository, jwtService),UsernamePasswordAuthenticationFilter.class)
				.build();
	}
	@Order(2)
	@Bean
	SecurityFilterChain refreshFilterChain(HttpSecurity httpSecurity) throws Exception{
		return httpSecurity.csrf(csrf->csrf.disable())
				.securityMatchers(mathers->mathers.requestMatchers("/api/v1/users/refreshLoginAndTokenRotation/**"))
				.authorizeHttpRequests(auth->auth.anyRequest().authenticated())
				.sessionManagement(management->management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(new RefreshFilter(accessTokenRepository, refreshTokenRepository, jwtService),UsernamePasswordAuthenticationFilter.class)
				.authenticationProvider(authenticationProvider())
				.build();
	}
	@Order(3)
	@Bean
	SecurityFilterChain anyRequest(HttpSecurity httpSecurity) throws Exception{
		return httpSecurity.csrf(csrf->csrf.disable())
				.securityMatchers(mathers->mathers.requestMatchers("/api/v1/**"))
				.authorizeHttpRequests(auth->auth.anyRequest().authenticated())
				.sessionManagement(management->management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(new JwtFilter(accessTokenRepository, refreshTokenRepository, jwtService),UsernamePasswordAuthenticationFilter.class)
				.authenticationProvider(authenticationProvider())
				.build();
	}
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

}