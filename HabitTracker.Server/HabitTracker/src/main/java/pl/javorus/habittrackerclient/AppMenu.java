package pl.javorus.habittrackerclient;

import pl.javorus.habittrackerclient.db.DataStorage;
import pl.javorus.habittrackerclient.model.Tag;
import pl.javorus.habittrackerclient.model.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class AppMenu {
    private DataStorage dataStorage;
    private Scanner scanner;

    public AppMenu() {
        dataStorage = DataStorage.getInstance();
        scanner = new Scanner(System.in);
    }


}