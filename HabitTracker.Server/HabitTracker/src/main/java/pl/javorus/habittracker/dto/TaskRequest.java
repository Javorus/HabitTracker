package pl.javorus.habittracker.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record TaskRequest(String title, String description, LocalDate startDate,
                          LocalTime duration, List<String> tagIds) { }
     