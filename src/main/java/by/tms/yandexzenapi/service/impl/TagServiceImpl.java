package by.tms.yandexzenapi.service.impl;

import by.tms.yandexzenapi.model.Post;
import by.tms.yandexzenapi.model.Tag;
import by.tms.yandexzenapi.repository.PostRepository;
import by.tms.yandexzenapi.repository.TagRepository;
import by.tms.yandexzenapi.service.TagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final PostRepository postRepository;
    private final TagRepository tagRepository;

    @Override
    public Tag save(long id, Tag tag) {
        Post byId = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post with id: " + id + " not found"));

        tag.setPost(byId);
        log.info("In method save - tag : {} successfully saved", tag);

        return tagRepository.save(tag);
    }

    @Override
    public Tag findById(long id) {
        Tag byId = tagRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tag with id: " + id + " not found"));

        log.info("IN method findById - tag: {} found by id: {}", byId, id);

        return byId;
    }

    @Override
    public List<Tag> findAll(long id) {
        List<Tag> tags = tagRepository.findAllTagsOfPost(id);

        if (tags.isEmpty()){
            throw new RuntimeException("No tags found!");
        }

        log.info("In method findAll - found {} tags of post with id : {}", tags.size(), id);

        return tags;
    }

    @Override
    public void delete(long id) {
        tagRepository.deleteById(id);
        log.info("In method delete - tag with id: {} successfully deleted", id);
    }

    @Override
    public boolean existByPostId(long id, String name) {
        return tagRepository.findAll().stream()
                .anyMatch(tag -> tag.getPost().getId() == id &&
                        tag.getName().equals(name));
    }

    @Override
    public List<Tag> findAllTagsOfPosts() {
        List<Tag> tags = tagRepository.findAll();

        if (tags.isEmpty()){
            throw new RuntimeException("No tags found!");
        }

        log.info("In method findAllTagsOfUsers - found {} tags of posts}", tags.size());

        return tags;
    }

    @Override
    public List<Tag> findPostsByTag(String name) {
        List<Tag> tags = tagRepository.findPostsByTag(name);

        if (tags.isEmpty()){
            throw new RuntimeException("No tags found!");
        }

        log.info("In method findUsersByTag - found {} tags of users}", tags.size());

        return tags;
    }
}
