package io.github.zhengye1.flashsale.auth;

import java.util.Locale;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse register(String username, String password) {
        String normalized = username.toLowerCase(Locale.ROOT);
        if (userRepository.existsByUsername(normalized)) {
            throw new UsernameAlreadyExistsException(normalized);
        }
        try {
            User saved = userRepository.save(new User(normalized, this.passwordEncoder.encode(password), Role.USER));
            return UserResponse.from(saved);
        } catch (DataIntegrityViolationException e) {
            if (e.getCause() instanceof ConstraintViolationException cve
                    && "uk_users_username".equals(cve.getConstraintName())) {
                throw new UsernameAlreadyExistsException(normalized);
            }
            throw e;
        }
    }
}
