package by.tms.yandexzenapi.service.impl;

import by.tms.yandexzenapi.model.Post;
import by.tms.yandexzenapi.model.User;
import by.tms.yandexzenapi.repository.PostRepository;
import by.tms.yandexzenapi.repository.UserRepository;
import by.tms.yandexzenapi.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Override
    public Post save(String username, Post post) {
        User byUsername = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User with username: " + username + " not found"));

        post.setUser(byUsername);
        log.info("In method save - post : {} successfully saved", post);

        return postRepository.save(post);
    }

    @Override
    public Post findById(long id) {
        Post byId = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post with id: " + id + " not found"));

        log.info("IN method findById - post: {} found by id: {}", byId, id);
        return byId;
    }

    @Override
    public List<Post> findAll(long id) {
        List<Post> posts = postRepository.findAllPostsOfUser(id);

        if (posts.isEmpty()){
            throw new RuntimeException("No posts found!");
        }

        log.info("In method findAll - found {} posts of user with id : {}", posts.size(), id);

        return posts;
    }

    @Override
    public Post update(long id, Post post) {
        Post byId = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post with id: " + id + " not found"));


        byId.setTitle(post.getTitle());
        byId.setDescription(post.getDescription());
        byId.setDateOfCreation(post.getDateOfCreation());
        byId.setCommentCounter(post.getCommentCounter());
        byId.setLikeCounter(post.getLikeCounter());
        log.info("IN method update - updated post : {} with id : {}", byId, id);

        return postRepository.save(byId);
    }

    @Override
    public void delete(long id) {
        postRepository.deleteById(id);
        log.info("In method delete - post with id: {} successfully deleted", id);
    }

    @Override
    public List<Post> findAllPostsOfUsers() {
        List<Post> posts = postRepository.findAll().stream()
                .sorted(Comparator.comparing(Post::getDateOfCreation).reversed())
                .collect(Collectors.toList());

        if (posts.isEmpty()){
            throw new RuntimeException("No posts found!");
        }

        log.info("In method findAllPostsOfUsers - found {} posts of users}", posts.size());

        return posts;
    }
}
