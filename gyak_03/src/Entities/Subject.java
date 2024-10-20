package Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.DayOfWeek;
import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.*;

@Entity
@Data
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String subjectName;

    public Subject() {}

    public Subject(String subjectName) {
        this.subjectName = subjectName;
    }

    // Getters, setters, equals, hashCode
}
