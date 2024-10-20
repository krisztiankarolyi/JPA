package Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.DayOfWeek;
import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.*;

@Entity
@Data
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @ManyToMany
    @JoinTable(name = "course_student",
        joinColumns = @JoinColumn(name = "course_id"),
        inverseJoinColumns = @JoinColumn(name = "student_id"))
    private Set<Student> students = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "course_professor",
        joinColumns = @JoinColumn(name = "course_id"),
        inverseJoinColumns = @JoinColumn(name = "professor_id"))
    private Set<Professor> professors = new HashSet<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DayOfWeek dayOfWeek;

    @Column(nullable = false, name = "\"hour\"")
    private int hour;

    @Column(nullable = false, name = "\"minute\"")
    private int minute;

    public Course() {}

    public Course(String name, Subject subject, DayOfWeek dayOfWeek, int hour, int minute) {
        this.name = name;
        this.subject = subject;
        this.dayOfWeek = dayOfWeek;
        this.hour = hour;
        this.minute = minute;
    }

    // Getters, setters, equals, hashCode
    
}