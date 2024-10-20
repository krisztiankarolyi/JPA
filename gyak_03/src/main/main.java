package main;

import jakarta.persistence.*;
import java.io.FileReader;
import java.time.DayOfWeek;
import java.util.*;
import com.opencsv.CSVReader;
import Entities.*;

public class main{

		   private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("university");
		   private static EntityManager em = emf.createEntityManager();

		    public static void main(String[] args) throws Exception {
		        em.getTransaction().begin();

		        loadStudents("src/files/students.csv");
		        loadProfessors("src/files/professors.csv");
		        loadCourses("src/files/courses.csv");

		        em.getTransaction().commit();
		        em.close();
		        emf.close();
	}

		    private static void loadStudents(String filePath) throws Exception {
		        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
		            String[] line;
		            while ((line = reader.readNext()) != null) {
		                if (!line[0].equals("id")) {
		                    Student student = new Student();
		                    student.setName(line[1]);
		                    em.persist(student);
		                    System.out.println(student);
		                }
		            }
		        }
		    }

		    private static void loadProfessors(String filePath) throws Exception {
		        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
		            String[] line;
		            while ((line = reader.readNext()) != null) {
		                if (!line[0].equals("id")) {
		                    Professor professor = new Professor();
		                    professor.setName(line[1]);
		                    em.persist(professor);
		                }
		            }
		        }
		    }

		    private static void loadCourses(String filePath) throws Exception {
		        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
		            String[] line;
		            while ((line = reader.readNext()) != null) {
		                if (!line[0].equals("id")) {
		                    Course course = new Course();
		                    course.setName(line[1]);

		                    Subject subject = em.find(Subject.class, Long.parseLong(line[2]));
		                    if(subject != null) {
		                    	course.setSubject(subject);
		                    }
		                    else {
		                    	System.out.print("Course not found");
		                    	subject = new Subject();
		                    	subject.setSubjectName("Random subject");
		                    	em.persist(subject);

		                    }


		                    course.setDayOfWeek(DayOfWeek.valueOf(line[3]));
		                    course.setHour(Integer.parseInt(line[4]));
		                    course.setMinute(Integer.parseInt(line[5]));

		                    // Hallgatók hozzáadása
		                    String[] studentIds = line[6].replace("\"", "").split(",");
		                    for (String studentId : studentIds) {
		                        Student student = em.find(Student.class, Long.parseLong(studentId));
		                        course.getStudents().add(student);
		                    }

		                    // Oktatók hozzáadása
		                    String[] professorIds = line[7].replace("\"", "").split(",");
		                    for (String professorId : professorIds) {
		                        Professor professor = em.find(Professor.class, Long.parseLong(professorId));
		                        course.getProfessors().add(professor);
		                    }

		                  em.persist(course);
		                }
		            }
		        }
		    }
}