package by.tms.yandexzenapi.service;

import by.tms.yandexzenapi.model.Comment;

import java.util.List;

public interface CommentService {
    Comment save(long id, Comment comment);
    Comment findById(long id);
    List<Comment> findAll(long id);
    void delete(long id);
    Comment update(long id, Comment comment);
}
