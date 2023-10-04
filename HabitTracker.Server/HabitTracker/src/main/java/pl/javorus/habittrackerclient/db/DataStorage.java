package pl.javorus.habittrackerclient.db;


import lombok.Getter;
import lombok.Setter;
import pl.javorus.habittrackerclient.model.Tag;
import pl.javorus.habittrackerclient.model.Task;

import java.util.ArrayList;

@Getter
@Setter
public final class DataStorage {
    private static DataStorage INSTANCE;
    String userId;
    ArrayList<Tag> tags;
    ArrayList<Task> tasks;
    private DataStorage(){

    }
    public static DataStorage getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new DataStorage();
        }
        return INSTANCE;
    }

}