package by.tms.yandexzenapi.dto;

import by.tms.yandexzenapi.model.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor @NoArgsConstructor
public class UserAdminDTO {

    private long id;

    private String username;

    private String firstName;

    private String lastName;

    private String email;

    private String country;

    private LocalDate birthDate;

    private Status status;
}
