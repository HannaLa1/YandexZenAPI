package by.tms.yandexzenapi.service.impl;

import by.tms.yandexzenapi.model.Tag;
import by.tms.yandexzenapi.model.User;
import by.tms.yandexzenapi.repository.TagRepository;
import by.tms.yandexzenapi.repository.UserRepository;
import by.tms.yandexzenapi.service.TagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final UserRepository userRepository;
    private final TagRepository tagRepository;

    @Override
    public Tag save(String username, Tag tag) {
        User byUsername = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User with username: " + username + " not found"));

        tag.setUser(byUsername);
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
        List<Tag> tags = tagRepository.findAllTagsOfUser(id);

        if (tags.isEmpty()){
            throw new RuntimeException("No tags found!");
        }

        log.info("In method findAll - found {} tags of user with id : {}", tags.size(), id);

        return tags;
    }

    @Override
    public void delete(long id) {
        tagRepository.deleteById(id);
        log.info("In method delete - tag with id: {} successfully deleted", id);
    }

    @Override
    public boolean existByUserUsername(String username, String usernameOfTag) {
        return tagRepository.findAll().stream()
                .anyMatch(tag -> tag.getUser().getUsername().equals(username) &&
                        tag.getName().equals(usernameOfTag));
    }

    @Override
    public List<Tag> findAllTagsOfUsers() {
        List<Tag> tags = tagRepository.findAll();

        if (tags.isEmpty()){
            throw new RuntimeException("No tags found!");
        }

        log.info("In method findAllTagsOfUsers - found {} tags of users}", tags.size());

        return tags;
    }

    @Override
    public List<Tag> findUsersByTag(String name) {
        List<Tag> tags = tagRepository.findUsersByTag(name);

        if (tags.isEmpty()){
            throw new RuntimeException("No tags found!");
        }

        log.info("In method findUsersByTag - found {} tags of users}", tags.size());

        return tags;
    }
}
