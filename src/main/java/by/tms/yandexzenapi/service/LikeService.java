package by.tms.yandexzenapi.service;

import by.tms.yandexzenapi.model.Like;

import java.util.List;

public interface LikeService {
    Like save(long id, Like like);
    Like findById(long id);
    List<Like> findAll(long id);
    void delete(long id);
    boolean existByPostId(long id, String username);
}
