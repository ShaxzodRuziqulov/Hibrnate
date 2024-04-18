package Hibernate.exercises.hibernate_multy_join_example;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "student_multy")
public class StudentMultiJoin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;

}
