package pl.javorus.habittrackerclient.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Task {
    private String name;
    private int duration;
    private List<Tag> tags;

}