package kz.spring.auth.auth.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.spring.auth.auth.config.UserDetailsImpl;
import kz.spring.auth.auth.dto.SignInDto;
import kz.spring.auth.auth.dto.SignUpDto;
import kz.spring.auth.auth.entity.User;
import kz.spring.auth.auth.exception.InvalidJwtException;
import kz.spring.auth.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

//    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
//    private final AuthenticationManager authenticationManager;
//    private final SecurityContextRepository securityContextRepository;

//    public AuthService(
//            PasswordEncoder passwordEncoder,
//            UserRepository userRepository,
//            AuthenticationManager authenticationManager,
//            SecurityContextRepository securityContextRepository
//    ) {
//        this.passwordEncoder = passwordEncoder;
//        this.userRepository = userRepository;
//        this.authenticationManager = authenticationManager;
//        this.securityContextRepository = securityContextRepository;
//    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
        return new UserDetailsImpl(user);
    }

    public UserDetails signUp(SignUpDto signUpData) throws InvalidJwtException {
        if (userRepository.findByLogin(signUpData.login()).isPresent()) {
            throw new InvalidJwtException("Username already exists");
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(signUpData.password());
        User newUser = new User(signUpData.login(), encryptedPassword, signUpData.role());
        userRepository.save(newUser);
        return new UserDetailsImpl(newUser);
    }
//
//    public void register(SignUpDto signUpData) {
//        if (userRepository.findByLogin(signUpData.login()).isEmpty()) {
//            throw new InvalidJwtException("Username already exists");
//        }
//
//        User user = User.builder()
//                .login(signUpData.login())
//                .password(passwordEncoder.encode(signUpData.password()))
//                .build();
//        userRepository.save(user);
//    }
//
//    public Authentication login(
//            SignInDto signInData,
//            HttpServletRequest request,
//            HttpServletResponse response
//    ) {
//        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
//                new UsernamePasswordAuthenticationToken(signInData.login(), signInData.password());
//        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
//        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
//        securityContext.setAuthentication(authenticate);
//
//        log.info("Authenticated and created session for {}", authenticate.getName());
//        return authenticate;
//    }
}