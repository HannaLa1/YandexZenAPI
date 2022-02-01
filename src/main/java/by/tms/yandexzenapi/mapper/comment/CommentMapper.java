package by.tms.yandexzenapi.mapper.comment;

import by.tms.yandexzenapi.dto.comment.CommentDTO;
import by.tms.yandexzenapi.model.Comment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    CommentDTO toCommentDTO(Comment comment);
    Comment toComment(CommentDTO commentDTO);
    List<CommentDTO> toCommentDTOList(List<Comment> comments);
}
