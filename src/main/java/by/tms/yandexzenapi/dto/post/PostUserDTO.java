package by.tms.yandexzenapi.dto.post;

import by.tms.yandexzenapi.dto.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor @NoArgsConstructor
public class PostUserDTO {

    private UserDTO user;

    private String title;

    private String description;

    private LocalDate dateOfCreation;

    private int commentCounter;

    private int likeCounter;
}
