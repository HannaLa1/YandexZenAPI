package by.tms.yandexzenapi.service;

import by.tms.yandexzenapi.model.Post;

import java.util.List;

public interface PostService {
    Post save(String username, Post post);
    Post findById(long id);
    List<Post> findAll(long id);
    Post update(long id, Post post);
    void delete(long id);
    List<Post> findAllPostsOfUsers();
}
