package by.tms.yandexzenapi.mapper.tag;

import by.tms.yandexzenapi.dto.tag.TagDTO;
import by.tms.yandexzenapi.model.Tag;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TagMapper {

    TagDTO toTagDTO(Tag tag);
    Tag toTag(TagDTO tagDTO);
    List<TagDTO> toTagDTOList(List<Tag> tags);
}
