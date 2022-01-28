package by.tms.yandexzenapi.mapper.post;

import by.tms.yandexzenapi.dto.post.PostDTO;
import by.tms.yandexzenapi.dto.post.PostUserDTO;
import by.tms.yandexzenapi.model.Post;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostUserMapper {

    PostUserDTO toPostUserDTO(Post post);
    Post toPost(PostUserDTO postUserDTO);
    List<PostUserDTO> toPostUserDTOList(List<Post> posts);
}
