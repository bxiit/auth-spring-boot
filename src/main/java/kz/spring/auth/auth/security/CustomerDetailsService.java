package kz.spring.auth.auth.security;

import kz.spring.auth.auth.model.Customer;
import kz.spring.auth.auth.storage.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomerDetailsService implements UserDetailsService {

    private static final String USER = "USER";
    private final CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findCustomerByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User %s not found".formatted(username)));
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(USER));
        return new User(
                customer.getUsername(),
                customer.getPassword(),
                authorities
        );
    }
}
