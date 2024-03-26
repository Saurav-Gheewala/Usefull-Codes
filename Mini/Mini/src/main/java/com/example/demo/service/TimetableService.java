package com.example.demo.service;

import com.example.demo.SampleData;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class TimetableService {

    private final Random random = new Random();

    public List<String> createTimetable() {
        List<String> timetable = new ArrayList<>();

        // Assuming each day starts at 9:00 AM and ends at 5:00 PM
        LocalTime startTime = LocalTime.of(9, 0);
        LocalTime endTime = LocalTime.of(17, 0);
        LocalTime currentTime = startTime;

        for (int day = 0; day < 6; day++) { // Loop for 6 days of the week
            timetable.add("Day " + (day + 1) + ":");

            for (int lecture = 0; lecture < 5; lecture++) { // 5 lectures per day
                if (currentTime.isBefore(endTime)) {
                    String course = SampleData.getCourses().get(lecture);
                    String teacher = SampleData.getTeachers().get(random.nextInt(SampleData.getTeachers().size()));
                    timetable.add("Lecture " + (lecture + 1) + ": " + course + " with " + teacher + " - " + currentTime + " - " + currentTime.plusHours(1));
                    currentTime = currentTime.plusHours(1);
                }
            }

            if (currentTime.isBefore(endTime)) { // 1 laboratory per day
                String laboratory = SampleData.getLaboratories().get(day % SampleData.getLaboratories().size());
                String teacher = SampleData.getTeachers().get(random.nextInt(SampleData.getTeachers().size()));
                timetable.add("Laboratory: " + laboratory + " with " + teacher + " - " + currentTime + " - " + currentTime.plusHours(2));
                currentTime = currentTime.plusHours(2);
            }

            // Reset currentTime for the next day
            currentTime = startTime;
        }

        return timetable;
    }
}
