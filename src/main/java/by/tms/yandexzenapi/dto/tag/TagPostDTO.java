package by.tms.yandexzenapi.dto.tag;

import by.tms.yandexzenapi.dto.post.PostUserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class TagPostDTO {

    private PostUserDTO post;

    private String name;
}
