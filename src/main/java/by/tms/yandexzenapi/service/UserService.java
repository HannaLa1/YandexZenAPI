package by.tms.yandexzenapi.service;

import by.tms.yandexzenapi.model.User;

import java.util.List;

public interface UserService {
    User findByUsername(String username);
    void registration(User user);
    List<User> findAll();
    void delete(long id);
}
