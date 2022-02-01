package by.tms.yandexzenapi.dto.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor @NoArgsConstructor
public class PostDTO {

    private String title;

    private String description;

    private LocalDate dateOfCreation;

    private int commentCounter;

    private int likeCounter;
}
