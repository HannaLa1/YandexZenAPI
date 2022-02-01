package by.tms.yandexzenapi.service;

import by.tms.yandexzenapi.model.Like;
import by.tms.yandexzenapi.model.Subscriber;

import java.util.List;

public interface SubscriberService {
    Subscriber subscribe(long id, Subscriber subscriber);
    Subscriber findById(long id);
    List<Subscriber> findAll(long id);
    void unsubscribe(long id);
    boolean existByUsername(String username);
    boolean existByUserId(long id, String username);
}
