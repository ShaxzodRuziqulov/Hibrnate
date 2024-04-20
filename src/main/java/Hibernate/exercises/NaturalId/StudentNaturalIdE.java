package Hibernate.exercises.NaturalId;

import lombok.Data;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Data
@Table(name = "stNaturalId")
public class StudentNaturalIdE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    @NaturalId
    private String passwordId;

}
