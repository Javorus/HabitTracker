package pl.javorus.habittracker.dto;

public class TagRequest {
    private String name;

    public TagRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
     