package by.tms.yandexzenapi.repository;

import by.tms.yandexzenapi.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {

    @Query(value = "from Tag where post.id  = ?1")
    List<Tag> findAllTagsOfPost(long id);

    @Query(value = "from Tag where name = ?1")
    List<Tag> findPostsByTag(String name);
}
