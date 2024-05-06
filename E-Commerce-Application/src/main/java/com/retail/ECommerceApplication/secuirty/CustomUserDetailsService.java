package com.retail.ECommerceApplication.secuirty;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.retail.ECommerceApplication.repository.UserRepository;
import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
	private UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByUserName(username)
				.map(user->new CustomUserDetails(user))
				.orElseThrow(()-> new UsernameNotFoundException("User Name Is Not Found!!!"));
	}

}
