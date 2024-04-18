package Hibernate.exercises.nativequery;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
@SqlResultSetMapping(
        name="StudentInfoMapping",
        classes = @ConstructorResult(
                targetClass = StudentNativeDto.class,
                columns = {
                        @ColumnResult(name = "id",type = Integer.class),
                        @ColumnResult(name = "name",type = String.class),
                        @ColumnResult(name = "surname",type = String.class),
                }
        )
)
@Data
@Entity
@Table(name = "student_native")
public class StudentNative {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String surName;
    private LocalDateTime createdTime;

}
