package by.tms.yandexzenapi.repository;

import by.tms.yandexzenapi.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = "from Post where user.id  = ?1")
    List<Post> findAllPostsOfUser(long id);
}
