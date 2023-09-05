package pl.javorus.habittracker.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tags")
public class Tag {
    @Id
    private String tagId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String name;


    public Tag(String tagId, User user, String name) {
        this.tagId = tagId;
        this.user = user;
        this.name = name;
    }

    public Tag(User user, String name) {
        this.user = user;
        this.name = name;
    }
}