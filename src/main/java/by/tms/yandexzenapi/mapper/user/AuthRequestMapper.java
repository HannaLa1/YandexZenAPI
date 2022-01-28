package by.tms.yandexzenapi.mapper.user;

import by.tms.yandexzenapi.dto.user.AuthRequestDTO;
import by.tms.yandexzenapi.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthRequestMapper {

    AuthRequestDTO toAuthRequestDTO(User user);
    User toUser(AuthRequestDTO authRequestDTO);
}
