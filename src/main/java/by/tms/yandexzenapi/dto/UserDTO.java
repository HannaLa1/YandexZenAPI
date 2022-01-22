package by.tms.yandexzenapi.dto;

import by.tms.yandexzenapi.model.Role;
import by.tms.yandexzenapi.model.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor
public class UserDTO {

    private long id;

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private String email;

    private String country;

    private LocalDate birthDate;

    private List<Role> roleList;

    private Status status;
}
