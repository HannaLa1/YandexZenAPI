package by.tms.yandexzenapi.mapper.tag;

import by.tms.yandexzenapi.dto.tag.TagDTO;
import by.tms.yandexzenapi.dto.tag.TagUserDTO;
import by.tms.yandexzenapi.model.Tag;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TagUserMapper {

    TagUserDTO toTagUserDTO(Tag tag);
    Tag toTag(TagUserDTO tagUserDTO);
    List<TagUserDTO> toTagUserDTOList(List<Tag> tags);
}
