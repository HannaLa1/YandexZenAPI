package by.tms.yandexzenapi.mapper.user;

import by.tms.yandexzenapi.dto.user.UserDTO;
import by.tms.yandexzenapi.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toUserDTO(User user);
    User toUser(UserDTO userDTO);
    List<UserDTO> toUserDTOList(List<User> users);
}
