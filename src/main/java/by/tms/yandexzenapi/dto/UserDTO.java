package by.tms.yandexzenapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor @NoArgsConstructor
public class UserDTO {

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private String email;

    private String country;

    private LocalDate birthDate;
}
