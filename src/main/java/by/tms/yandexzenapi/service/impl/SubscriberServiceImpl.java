package by.tms.yandexzenapi.service.impl;

import by.tms.yandexzenapi.model.Subscriber;
import by.tms.yandexzenapi.model.User;
import by.tms.yandexzenapi.repository.SubscriberRepository;
import by.tms.yandexzenapi.repository.UserRepository;
import by.tms.yandexzenapi.service.SubscriberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SubscriberServiceImpl implements SubscriberService {

    private final UserRepository userRepository;
    private final SubscriberRepository subscriberRepository;

    @Override
    public Subscriber subscribe(long id, Subscriber subscriber) {
        User byId = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User with id: " + id + " not found"));

        byId.setSubscriberCounter(byId.getSubscriberCounter() + 1);
        subscriber.setUser(byId);
        log.info("In method save - subscriber : {} successfully saved", subscriber);

        return subscriberRepository.save(subscriber);
    }

    @Override
    public Subscriber findById(long id) {
        Subscriber byId = subscriberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subscriber with id: " + id + " not found"));

        log.info("IN method findById - subscriber: {} found by id: {}", byId, id);
        return byId;
    }

    @Override
    public List<Subscriber> findAll(long id) {
        List<Subscriber> subscribers = subscriberRepository.findAllSubscribersOfUser(id);

        if (subscribers.isEmpty()){
            throw new RuntimeException("No subscribers found!");
        }

        log.info("In method findAll - found {} subscribers of user with id : {}", subscribers.size(), id);

        return subscribers;
    }

    @Override
    public void unsubscribe(long id) {
        Subscriber byId = subscriberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subscriber with id: " + id + " not found"));

        User user = byId.getUser();
        user.setSubscriberCounter(user.getSubscriberCounter() - 1);
        subscriberRepository.deleteById(id);
        log.info("In method delete - subscriber with id: {} successfully deleted", id);
    }

    @Override
    public boolean existByUsername(String username) {
        return subscriberRepository.existsByUsername(username);
    }

    @Override
    public boolean existByUserId(long id, String username) {
        return subscriberRepository.findAll().stream()
                .anyMatch(subscriber -> subscriber.getUser().getId() == id &&
                        subscriber.getUsername().equals(username));
    }
}
