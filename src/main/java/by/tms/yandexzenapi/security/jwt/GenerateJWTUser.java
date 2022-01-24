package by.tms.yandexzenapi.security.jwt;

import by.tms.yandexzenapi.model.Role;
import by.tms.yandexzenapi.model.Status;
import by.tms.yandexzenapi.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class GenerateJWTUser {

    public GenerateJWTUser() {
    }

    public static JWTUser create(User user){
        return new JWTUser(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                mapToGrantedAuthorities(new ArrayList<>(user.getRoleList())),
                user.getStatus().equals(Status.ACTIVE)
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getTypeOfRole()))
                .collect(Collectors.toList());
    }
}
