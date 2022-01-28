package by.tms.yandexzenapi.mapper.like;

import by.tms.yandexzenapi.dto.like.LikeDTO;
import by.tms.yandexzenapi.model.Like;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LikeMapper {

    LikeDTO toLikeDTO(Like like);
    Like toLike(LikeDTO likeDTO);
    List<LikeDTO> toLikeDTOList(List<Like> likes);
}
