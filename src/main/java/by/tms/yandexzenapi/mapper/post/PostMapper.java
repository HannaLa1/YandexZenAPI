package by.tms.yandexzenapi.mapper.post;

import by.tms.yandexzenapi.dto.post.PostDTO;
import by.tms.yandexzenapi.model.Post;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {

    PostDTO toPostDTO(Post post);
    Post toPost(PostDTO postDTO);
    List<PostDTO> toPostDTOList(List<Post> posts);
}
