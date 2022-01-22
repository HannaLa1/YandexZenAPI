package by.tms.yandexzenapi.mapper;

import by.tms.yandexzenapi.dto.UserDTO;
import by.tms.yandexzenapi.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toUserDTO(User user);
    User toUser(UserDTO userDTO);
}
