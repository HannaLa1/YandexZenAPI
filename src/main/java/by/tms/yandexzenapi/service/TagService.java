package by.tms.yandexzenapi.service;

import by.tms.yandexzenapi.model.Tag;

import java.util.List;

public interface TagService {
    Tag save(long id, Tag tag);
    Tag findById(long id);
    List<Tag> findAll(long id);
    void delete(long id);
    boolean existByPostId(long id, String username);
    List<Tag> findAllTagsOfPosts();
    List<Tag> findPostsByTag(String name);
}
