package Hibernate.exercises.NaturalId;

import lombok.Data;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Data
@Table(name = "subjectNaturalIdE")
public class SubjectNaturalIdE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @NaturalId
    private String localCode;
    @NaturalId
    private String generalCode;
}
