package by.tms.yandexzenapi.mapper;

import by.tms.yandexzenapi.dto.UserAdminDTO;
import by.tms.yandexzenapi.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserAdminMapper {

    UserAdminDTO toUserAdminDTO(User user);
    User toUser(UserAdminDTO userAdminDTO);
    List<UserAdminDTO> toUserAdminDTOList(List<User> user);
}
