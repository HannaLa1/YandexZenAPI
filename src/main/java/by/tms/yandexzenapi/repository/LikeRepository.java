package by.tms.yandexzenapi.repository;

import by.tms.yandexzenapi.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {

    @Query(value = "from Like where post.id  = ?1")
    List<Like> findAllLikesOfPost(long id);
}
