package by.tms.yandexzenapi.service.impl;

import by.tms.yandexzenapi.model.Like;
import by.tms.yandexzenapi.model.Post;
import by.tms.yandexzenapi.repository.LikeRepository;
import by.tms.yandexzenapi.repository.PostRepository;
import by.tms.yandexzenapi.service.LikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final PostRepository postRepository;
    private final LikeRepository likeRepository;

    @Override
    public Like save(long id, Like like) {
        Post byId = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post with id: " + id + " not found"));

        byId.setLikeCounter(byId.getLikeCounter() + 1);
        like.setPost(byId);
        log.info("In method save - like : {} successfully saved", like);

        return likeRepository.save(like);
    }

    @Override
    public Like findById(long id) {
        Like byId = likeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Like with id: " + id + " not found"));

        log.info("IN method findById - like: {} found by id: {}", byId, id);
        return byId;
    }

    @Override
    public List<Like> findAll(long id) {
        List<Like> likes = likeRepository.findAllLikesOfPost(id);

        if (likes.isEmpty()){
            throw new RuntimeException("No likes found!");
        }

        log.info("In method findAll - found {} likes of post with id : {}", likes.size(), id);

        return likes;
    }

    @Override
    public void delete(long id) {
        Like byId = likeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Like with id: " + id + " not found"));

        Post post = byId.getPost();
        post.setLikeCounter(post.getLikeCounter() - 1);
        likeRepository.deleteById(id);
        log.info("In method delete - like with id: {} successfully deleted", id);
    }

    @Override
    public boolean existByPostId(long id, String username) {
        return likeRepository.findAll().stream()
                .anyMatch(like -> like.getPost().getId() == id &&
                        like.getNameOfUser().equals(username));
    }
}
