package by.tms.yandexzenapi.security;

import by.tms.yandexzenapi.model.User;
import by.tms.yandexzenapi.security.jwt.GenerateJWTUser;
import by.tms.yandexzenapi.security.jwt.JWTUser;
import by.tms.yandexzenapi.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class JWTUserDetailsService implements UserDetailsService {
    private final UserService service;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = service.findByUsername(username);

        if (user == null){
            throw new UsernameNotFoundException("User with username: " + username + "not found");
        }

        JWTUser jwtUser = GenerateJWTUser.create(user);
        log.info("In method loadUserByUsername - user with username: {} successfully loaded", username);

        return jwtUser;
    }
}
