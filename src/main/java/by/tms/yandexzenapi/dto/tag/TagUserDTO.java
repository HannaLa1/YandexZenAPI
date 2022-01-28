package by.tms.yandexzenapi.dto.tag;

import by.tms.yandexzenapi.dto.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class TagUserDTO {

    private UserDTO user;

    private String name;
}
