package Hibernate.exercises.caching;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "studentcaching")
public class StudentCaching {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String surname;
    private LocalDateTime createdDate;
}
