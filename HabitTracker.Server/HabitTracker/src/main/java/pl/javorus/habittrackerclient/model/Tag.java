package pl.javorus.habittrackerclient.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Tag {
    private String name;
    private List<Task> tasks;


}