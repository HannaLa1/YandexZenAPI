package by.tms.yandexzenapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "likes")
@Data
@AllArgsConstructor @NoArgsConstructor
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nameOfUser;

    @ManyToOne
    private Post post;

    private boolean isActive;
}
