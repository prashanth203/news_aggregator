package com.airtribe.news_aggregator.service;

import com.airtribe.news_aggregator.entity.AuthUser;
import com.airtribe.news_aggregator.entity.VerificationToken;
import com.airtribe.news_aggregator.model.AuthUserModel;
import com.airtribe.news_aggregator.model.JwtUtil;
import com.airtribe.news_aggregator.repository.AuthUserRepository;
import com.airtribe.news_aggregator.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AuthService implements UserDetailsService {
    @Autowired
    private AuthUserRepository authUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    public AuthUser register(AuthUserModel user) {
        System.out.println("AuthUser Service");
        AuthUser newUser = new AuthUser();

        newUser.setUserName(user.getUserName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setEnabled(false);
        newUser.setRole("USER");

        return authUserRepository.save(newUser);
    }

    public VerificationToken createVerificationToken(String token, AuthUser storedUser) {
        VerificationToken verificationToken = new VerificationToken(token, storedUser);

        return verificationTokenRepository.save(verificationToken);
    }

    public boolean verifyRegistration(String token) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);
        boolean hasTokenExpired = verificationToken.getExpirationDate().before(new Date());

        if(hasTokenExpired) {
            return false;
        }

        verificationToken.getUser().setEnabled(true);

        AuthUser user = authUserRepository.save(verificationToken.getUser());
        verificationTokenRepository.delete(verificationToken);

        return user.isEnabled();
    }

    public String signin(String email, String password) {
        AuthUser user = authUserRepository.findByEmail(email);

        if(user == null || !user.isEnabled()) {
            return "Failed to authenticate user";
        }

        boolean isAuthenticated = authenticateUser(user, password);

        if(isAuthenticated) {
            return JwtUtil.generateToken(user.getEmail());
        } else {
            return "Failed to authenticate user";
        }
    }

    private boolean authenticateUser(AuthUser user, String password) {
        boolean doesPasswordMatch = passwordEncoder.matches(password, user.getPassword());

        if(!doesPasswordMatch) return false;

        UserDetails loadedUser = loadUserByUsername(user.getEmail());

        Authentication authentication = new UsernamePasswordAuthenticationToken(loadedUser, password, loadedUser.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthUser user = authUserRepository.findByEmail(username);

        if(user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return new User(user.getEmail(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority(user.getRole())));
    }
}