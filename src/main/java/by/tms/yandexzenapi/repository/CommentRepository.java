package by.tms.yandexzenapi.repository;

import by.tms.yandexzenapi.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = "from Comment where post.id  = ?1")
    List<Comment> findAllCommentsOfPost(long id);
}
