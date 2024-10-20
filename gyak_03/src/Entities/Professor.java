package Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.DayOfWeek;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "professors")
    private Set<Course> courses = new HashSet<>();

    public Professor() {}

    public Professor(String name) {
        this.name = name;
    }

    // Getters, setters, equals, hashCode
}
