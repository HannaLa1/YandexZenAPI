package by.tms.yandexzenapi.service.impl;

import by.tms.yandexzenapi.model.Role;
import by.tms.yandexzenapi.model.Status;
import by.tms.yandexzenapi.model.User;
import by.tms.yandexzenapi.repository.RoleRepository;
import by.tms.yandexzenapi.repository.UserRepository;
import by.tms.yandexzenapi.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public User findByUsername(String username) {
        User byUsername = userRepository.findByUsername(username);
        log.info("In method findByUsername - user: {} successfully found by username {}", byUsername, username);

        return byUsername;
    }

    @Override
    public void registration(User user) {
        List<Role> roles = new ArrayList<>();
        Role role = new Role();
        role.setTypeOfRole("USER");
        roles.add(role);
        user.setRoleList(roles);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoleList(roles);
        user.setStatus(Status.ACTIVE);
        role.setUser(user);
        User regUser = userRepository.save(user);
        roleRepository.save(role);
        log.info("In method registration - user : {} successfully sign up", regUser);
    }

    @Override
    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        log.info("In method findAll - {} users found", users.size());

        return users;
    }

    @Override
    public User findById(long id) {
        User byId = userRepository.findById(id).orElse(null);

        if (byId == null) {
            log.warn("IN method findById - no user found by id: {}", id);
            return null;
        }

        log.info("IN method findById - user: {} found by id: {}", byId, id);
        return byId;
    }

    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
        log.info("In method delete - user with id: {} successfully deleted", id);
    }

    @Override
    public boolean existByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
