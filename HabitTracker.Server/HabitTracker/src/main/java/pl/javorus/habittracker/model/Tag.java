package pl.javorus.habittracker.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID tagId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String name;


    public Tag(UUID tagId, User user, String name) {
        this.tagId = tagId;
        this.user = user;
        this.name = name;
    }

    public Tag(User user, String name) {
        this.user = user;
        this.name = name;
    }

    public Tag() {

    }


    public UUID getTagId() {
        return tagId;
    }

    public void setTagId(UUID tagId) {
        this.tagId = tagId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}