package by.tms.yandexzenapi.repository;

import by.tms.yandexzenapi.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {

    @Query(value = "from Tag where user.id  = ?1")
    List<Tag> findAllTagsOfUser(long id);

    @Query(value = "from Tag where name = ?1")
    List<Tag> findUsersByTag(String name);
}
