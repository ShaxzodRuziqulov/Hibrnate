package Hibernate.exercises.hibernate_select_in_join;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "student_select")
public class StudentSelect {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;
    private String phone;
    private LocalDate birthDate;

    // In StudentSelect class
    @OneToMany()
    private List<Address> addresses;

}
