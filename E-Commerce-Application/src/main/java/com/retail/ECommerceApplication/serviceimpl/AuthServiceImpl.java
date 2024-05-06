package com.retail.ECommerceApplication.serviceimpl;
import java.net.CookieManager;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;

import org.apache.naming.factory.SendMailFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.retail.ECommerceApplication.cache.CacheStore;
import com.retail.ECommerceApplication.entity.AccessToken;
import com.retail.ECommerceApplication.entity.Customer;
import com.retail.ECommerceApplication.entity.RefreshToken;
import com.retail.ECommerceApplication.entity.Seller;
import com.retail.ECommerceApplication.entity.User;
import com.retail.ECommerceApplication.enums.UserRole;
import com.retail.ECommerceApplication.exception.EmailIsNotUniqueException;
import com.retail.ECommerceApplication.exception.IllegalArgumentException;
import com.retail.ECommerceApplication.exception.InvalidCredentialException;
import com.retail.ECommerceApplication.exception.OtpIsNotValidException;
import com.retail.ECommerceApplication.jwt.JwtService;
import com.retail.ECommerceApplication.mail_service.MailService;
import com.retail.ECommerceApplication.mail_service.MessageModel;
import com.retail.ECommerceApplication.repository.AccessTokenRepository;
import com.retail.ECommerceApplication.repository.CustomerRepository;
import com.retail.ECommerceApplication.repository.RefreshTokenRepository;
import com.retail.ECommerceApplication.repository.SellerRepository;
import com.retail.ECommerceApplication.repository.UserRepository;
import com.retail.ECommerceApplication.requestdto.AuthRequestDto;
import com.retail.ECommerceApplication.requestdto.OtpRequest;
import com.retail.ECommerceApplication.requestdto.UserRequestDto;
import com.retail.ECommerceApplication.responsedto.AuthResponseDto;
import com.retail.ECommerceApplication.responsedto.UserResponseDto;
import com.retail.ECommerceApplication.service.AuthService;
import com.retail.ECommerceApplication.utility.ResponseStructure;
import com.retail.ECommerceApplication.utility.SimpleResponseStructure;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
@Service
public class AuthServiceImpl implements AuthService{
	private UserRepository userRepository;
	private CacheStore<String> otpCache;
	private CacheStore<User> userCache;
	private ResponseStructure<UserResponseDto> responseStructure;
	private SimpleResponseStructure simpleResponseStructure;
	private MailService mailService;
	private PasswordEncoder passwordEncoder;
	private AuthenticationManager authenticationManager;
	private JwtService jwtService;
	private ResponseStructure<AuthResponseDto> authResponseStructure;
	private AccessTokenRepository accessTokenRepository;
	private RefreshTokenRepository refreshTokenRepository;
	@Value("${myapp.jwt.access.expiration}")
	private long AccessExpiration;

	@Value("${myapp.jwt.refresh.expiration}")
	private long RefreshExpiration;
	public AuthServiceImpl(UserRepository userRepository, CacheStore<String> otpCache, CacheStore<User> userCache,
			ResponseStructure<UserResponseDto> responseStructure, SimpleResponseStructure simpleResponseStructure,
			MailService mailService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager,
			JwtService jwtService, ResponseStructure<AuthResponseDto> authResponseStructure,
			AccessTokenRepository accessTokenRepository, RefreshTokenRepository refreshTokenRepository) {
		super();
		this.userRepository = userRepository;
		this.otpCache = otpCache;
		this.userCache = userCache;
		this.responseStructure = responseStructure;
		this.simpleResponseStructure = simpleResponseStructure;
		this.mailService = mailService;
		this.passwordEncoder = passwordEncoder;
		this.authenticationManager = authenticationManager;
		this.jwtService = jwtService;
		this.authResponseStructure = authResponseStructure;
		this.accessTokenRepository = accessTokenRepository;
		this.refreshTokenRepository = refreshTokenRepository;
	}
	@Override
	public ResponseEntity<SimpleResponseStructure> registerUser(UserRequestDto userRequestDto)  {
		if(userRepository.existsByEmail(userRequestDto.getEmail()))
			throw new EmailIsNotUniqueException("Failed To Register User!!!");

		User user=mapToChildEntity(userRequestDto);
		String otp=generateOTP();
		otpCache.add(user.getEmail(),otp);
		userCache.add(user.getEmail(), user);

		//Send Mail With OTP
		try {
			sendMail(user,otp);
		} catch (MessagingException e) {
			throw new IllegalArgumentException("Email Is Not Valid!!!");
		}

		//Return The Data
		return ResponseEntity.ok(simpleResponseStructure
				.setStatus(HttpStatus.ACCEPTED.value())
				.setMessage("Verify OTP Sent Through Mail To Complete Registration | OTP Expire In 01 Minute "+otp));
	}
	private void sendMail(User user, String otp) throws MessagingException {
		MessageModel model=MessageModel.builder()
				.to(user.getEmail())
				.subject("Verify Your OTP")
				.text("<img src='https://static.startuptalky.com/2023/10/Flipkart-Marketing-Strategies-StartupTalky.jpg' "
						+ "style='height:200px; width:500px'>"
						+"<p>Hi,<br>"+user.getDisplayName()
						+ ". Thanks For Giving Interest In E-Commerce Application.<br>"
						+ " Please Verify Your Mail Id Using This OTP Given Below.</p>"
						+ "<br>"
						+"<h1 style='color:blue'>"+otp+"</h1>"
						+ "<img src='https://play-lh.googleusercontent.com/q8hxnbpJCYfHipSOG_5tZe5jK_89T6QIsqrEklvGpMFKH8b98pDHJf2tPcn2bxEN96ON' \r\n"
						+ "     style='height: 100px; width: 100px;'>\r\n"
						+ ""
						+ "<br>"
						+"With Best Regards"
						+"<h3>E-Commerce Application</h3>"

						)
				.build();
		mailService.sendMailMessage(model);
	}
	private String generateOTP() {
		return String.valueOf(new Random().nextInt(100000,999999));
	}
	@Override
	public ResponseEntity<ResponseStructure<UserResponseDto>> verifyOtp(OtpRequest otpRequest) {
		if(otpCache.get(otpRequest.getEmail())==null)
			throw new IllegalArgumentException("Otp Is Expired!!");
		if(!otpCache.get(otpRequest.getEmail()).equals(otpRequest.getOtp()))
			throw new OtpIsNotValidException("Invalid Opeartion!!!");
		User user = userCache.get(otpRequest.getEmail());
		if(user==null)
			throw new IllegalArgumentException("Registration Session Is Expired!!!");
		user.setEmailVerified(true);
		userRepository.save(user);

		return ResponseEntity.ok(responseStructure
				.setStatusCode(HttpStatus.CREATED.value())
				.setStatusMessage("Otp Is Verified Successfully")
				.setStatusData(mapToUserResponse(user)));
	}

	private <T extends User> T mapToChildEntity(UserRequestDto userRequestDto){
		UserRole role = userRequestDto.getUserRole();
		User user=null;
		switch (role) {
		case SELLER -> user=new Seller();
		case CUSTOMER->user=new Customer();
		default ->throw new IllegalArgumentException("Invalid Input!!!");
		}
		user.setDisplayName(userRequestDto.getName());
		user.setUserName(userRequestDto.getEmail().split("@gmail.com")[0]);
		user.setEmail(userRequestDto.getEmail());
		user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
		user.setUserRole(userRequestDto.getUserRole());
		user.setEmailVerified(false);
		return (T) user;
	}
	public UserResponseDto mapToUserResponse(User user) {
		return	UserResponseDto.builder()
				.userId(user.getUserId())
				.userName(user.getUserName())
				.displayName(user.getDisplayName())
				.email(user.getEmail())
				.isEmailVerified(user.isEmailVerified())
				.userRole(user.getUserRole()).build();
	}

	@Override
	public ResponseEntity<ResponseStructure<AuthResponseDto>> userLogin(@Valid AuthRequestDto authRequestDto,String accessToken,String refreshToken) {
		//Invalid Authenticated
//		if((accessToken == null && refreshToken!=null) || (accessToken!=null && refreshToken!=null)){
//			throw new IllegalArgumentException("User Already logged in and ask to send a refresh request instead");
//		}
		if(accessToken != null && refreshToken!=null){
			throw new IllegalArgumentException("User Already logged in and ask to send a refresh request instead");
		}

		String userName = authRequestDto.getUserName().split("@gmail.com")[0];
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(userName, authRequestDto.getPassword()));
		if(!authentication.isAuthenticated()) throw new InvalidCredentialException("Invalid Credential!!!");

		SecurityContextHolder.getContext().setAuthentication(authentication);

		//generate access and refresh token
		HttpHeaders headers=new HttpHeaders();
		return userRepository.findByUserName(userName).map(user->{
			generateAccessToken(user,headers);
			generateRefreshToken(user,headers);
			return ResponseEntity.ok().headers(headers)
					.body(authResponseStructure.setStatusCode(HttpStatus.OK.value())
							.setStatusMessage("Login Is Done Successfully")
							.setStatusData(mapToAuthResponse(user)));
		}).get();

	}
	@Override
	public ResponseEntity<SimpleResponseStructure> logout(String refreshToken, String accessToken) {
		if (accessToken == null || refreshToken == null) {
			throw new IllegalArgumentException("User Is Not Logged In!!!");
		}

		// resetting tokens with blank value and 0 maxAge
		String userName = jwtService.getUserName(accessToken);
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.SET_COOKIE, configureLogoutCookies("rt", "", 0));
		headers.add(HttpHeaders.SET_COOKIE, configureLogoutCookies("at", "", 0));

		// Blocking The Cookie
		blockedAccessToken(accessToken);
		blockedRefreshToken(refreshToken);

		return ResponseEntity.ok().headers(headers)
				.body(simpleResponseStructure.setStatus(HttpStatus.OK.value())
						.setMessage("Logout Is Done Successfully"));
	}
	@Override
	public ResponseEntity<ResponseStructure<AuthResponseDto>> refreshLogin(String accessToken, String refreshToken) {
		System.out.println(refreshToken);
		if(refreshToken==null) {
			throw new IllegalArgumentException("User Is Not SignIn!!!!");
		}

		if(accessToken!=null) {
			accessTokenRepository.findByToken(accessToken).ifPresent(token->{
				System.out.println(token.getTokenId());
				token.setBlocked(true);
				accessTokenRepository.save(token);
			});
		}
		Date date = jwtService.getIssueDate(refreshToken);
		String userName = jwtService.getUserName(refreshToken);

		HttpHeaders headers=new HttpHeaders();
		return userRepository.findByUserName(userName).map(user->{

			if((new Date().getTime()-date.getTime())/3600000>24) {
				refreshTokenRepository.findByToken(refreshToken).ifPresent(token->{
					System.out.println(token.getTokenId());
					token.setBlocked(true);
					refreshTokenRepository.save(token);
				});
				generateRefreshToken(user, headers);

			}
			else
				headers.add(HttpHeaders.SET_COOKIE, configureCookie("rt",refreshToken,RefreshExpiration));

			generateAccessToken(user,headers);

			return ResponseEntity.ok().headers(headers)
					.body(authResponseStructure.setStatusCode(HttpStatus.OK.value())
							.setStatusMessage("Token Is Refreshed Successfully")
							.setStatusData(mapToAuthResponse(user)));
		}).get();
	}
	//----------------------------------------------------------------------------------------------------------------
	private void blockedAccessToken(String accessToken) {
		accessTokenRepository.findByToken(accessToken).ifPresent(at->{
			at.setBlocked(true);
			accessTokenRepository.save(at);
		});
	}
	private void blockedRefreshToken(String refreshToken) {
		refreshTokenRepository.findByToken(refreshToken).ifPresent(rt->{
			rt.setBlocked(true);
			refreshTokenRepository.save(rt);
		});
	}
	private AuthResponseDto mapToAuthResponse(User user) {
		return AuthResponseDto.builder()
				.userId(user.getUserId())
				.userName(user.getUserName())
				.userRole(user.getUserRole())
				.accessExpiration(AccessExpiration)
				.accessExpiry(LocalDateTime.now().plusHours(1))
				.refreshExpiry(LocalDateTime.now().plusDays(15))
				.refreshExpiration(RefreshExpiration)
				.build();
	}
	private void generateAccessToken(User user,HttpHeaders headers) {
		String token=jwtService.generateAccessToken(user,user.getUserRole().name());
		headers.add(HttpHeaders.SET_COOKIE,configureCookie("at", token,AccessExpiration));
		//Store The Token to the Database
		AccessToken accessToken=new AccessToken();
		accessToken.setToken(token);
		accessToken.setBlocked(false);
		accessToken.setExpiration(LocalDateTime.now().plusSeconds(AccessExpiration/1000));
		accessToken.setUser(user);
		accessTokenRepository.save(accessToken);
	}

	private void generateRefreshToken(User user,HttpHeaders headers) {
		String token=jwtService.generateRefreshToken(user,user.getUserRole().name());
		headers.add(HttpHeaders.SET_COOKIE,configureCookie("rt", token,RefreshExpiration));
		//Store The Token to the Database
		RefreshToken refreshToken=new RefreshToken();
		refreshToken.setToken(token);
		refreshToken.setBlocked(false);
		refreshToken.setExpiration(LocalDateTime.now().plusSeconds(RefreshExpiration/1000));
		refreshToken.setUser(user);
		refreshTokenRepository.save(refreshToken);
	}
	private String configureCookie(String name,String value,long maxAge) {
		return ResponseCookie.from(name,value)
				.domain("localhost")
				.path("/")
				.httpOnly(true)
				.secure(false)
				.maxAge(Duration.ofMillis(maxAge))
				.sameSite("Lax")
				.build().toString();
	}
	private String configureLogoutCookies(String name,String value,long maxAge) {
		return ResponseCookie.from(name,"")
				.domain("localhost")
				.path("/")
				.httpOnly(true)
				.maxAge(0)
				.sameSite("Lax")
				.build().toString();
	}
	



}