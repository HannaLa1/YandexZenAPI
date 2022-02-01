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

    @Query(value = "SELECT distinct a.id, a.post_id, a.name FROM Tags a INNER JOIN Tags b ON a.name = b.name WHERE a.id <> b.id", nativeQuery = true)
    List<Tag> findAllTagsOfPosts();
}
