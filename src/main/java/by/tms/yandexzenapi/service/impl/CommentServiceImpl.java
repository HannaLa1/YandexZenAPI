package by.tms.yandexzenapi.service.impl;

import by.tms.yandexzenapi.model.Comment;
import by.tms.yandexzenapi.model.Post;
import by.tms.yandexzenapi.repository.CommentRepository;
import by.tms.yandexzenapi.repository.PostRepository;
import by.tms.yandexzenapi.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Override
    public Comment save(long id, Comment comment) {
        Post byId = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post with id: " + id + " not found"));

        byId.setCommentCounter(byId.getCommentCounter() + 1);
        comment.setPost(byId);
        log.info("In method save - comment : {} successfully saved", comment);

        return commentRepository.save(comment);
    }

    @Override
    public Comment findById(long id) {
        Comment byId = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment with id: " + id + " not found"));

        log.info("IN method findById - comment: {} found by id: {}", byId, id);
        return byId;
    }

    @Override
    public List<Comment> findAll(long id) {
        List<Comment> comments = commentRepository.findAllCommentsOfPost(id).stream()
                .sorted(Comparator.comparing(Comment::getDateOfComment).reversed())
                .collect(Collectors.toList());

        if (comments.isEmpty()){
            throw new RuntimeException("No comments found!");
        }

        log.info("In method findAll - found {} comments of post with id : {}", comments.size(), id);

        return comments;
    }

    @Override
    public void delete(long id) {
        Comment byId = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment with id: " + id + " not found"));

        Post post = byId.getPost();
        post.setCommentCounter(post.getCommentCounter() - 1);
        commentRepository.deleteById(id);
        log.info("In method delete - comment with id: {} successfully deleted", id);
    }

    @Override
    public Comment update(long id, Comment comment) {
        Comment byId = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment with id: " + id + " not found"));

        byId.setNameOfUser(comment.getNameOfUser());
        byId.setDateOfComment(comment.getDateOfComment());
        byId.setDescriptionOfComment(comment.getDescriptionOfComment());
        log.info("IN method update - updated comment : {} with id : {}", byId, id);

        return commentRepository.save(byId);
    }
}
