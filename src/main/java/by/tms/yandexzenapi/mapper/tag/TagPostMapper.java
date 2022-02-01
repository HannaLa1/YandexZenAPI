package by.tms.yandexzenapi.mapper.tag;

import by.tms.yandexzenapi.dto.tag.TagPostDTO;
import by.tms.yandexzenapi.model.Tag;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TagPostMapper {

    TagPostDTO toTagUserDTO(Tag tag);
    Tag toTag(TagPostDTO tagUserDTO);
    List<TagPostDTO> toTagUserDTOList(List<Tag> tags);
}
