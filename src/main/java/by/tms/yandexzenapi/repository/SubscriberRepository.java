package by.tms.yandexzenapi.repository;

import by.tms.yandexzenapi.model.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {

    @Query(value = "from Subscriber where user.id  = ?1")
    List<Subscriber> findAllSubscribersOfUser(long id);

    boolean existsByUsername(String username);
}
