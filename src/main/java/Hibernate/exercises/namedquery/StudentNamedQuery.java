package Hibernate.exercises.namedquery;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@NamedQueries(
        {@NamedQuery(
                name = "findAllStudentByName",
                query = "from StudentNamedQuery s where s.name=:name"
        ), @NamedQuery(
                name = "findStudentById",
                query = "from StudentNamedQuery s where s.id=:id "
        ),
        }
)
@NamedNativeQueries(
        {
                @NamedNativeQuery(
                        name = "findAllStudentByNativeQuery",
                        query = "select * from student_named_query s where s.name=:name ",
                        resultClass = StudentNamedQuery.class
                )
        }
)
@Entity
@Data
@Table(name = "student_named_query")
public class StudentNamedQuery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private LocalDateTime createdDate;
}
