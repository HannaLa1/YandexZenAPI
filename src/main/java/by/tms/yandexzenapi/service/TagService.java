package by.tms.yandexzenapi.service;

import by.tms.yandexzenapi.model.Post;
import by.tms.yandexzenapi.model.Tag;

import java.util.List;

public interface TagService {
    Tag save(String username, Tag tag);
    Tag findById(long id);
    List<Tag> findAll(long id);
    void delete(long id);
    boolean existByUserUsername(String username, String usernameOfTag);
    List<Tag> findAllTagsOfUsers();
    List<Tag> findUsersByTag(String name);
}
