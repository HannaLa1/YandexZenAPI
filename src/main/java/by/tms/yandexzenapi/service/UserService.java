package by.tms.yandexzenapi.service;

import by.tms.yandexzenapi.model.User;

import java.util.List;

public interface UserService {
    User findByUsername(String username);
    void registration(User user);
    List<User> findAll();
    User findById(long id);
    void delete(long id);
    boolean existByUsername(String username);
    boolean existByEmail(String email);
}
