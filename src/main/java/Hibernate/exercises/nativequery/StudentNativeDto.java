package Hibernate.exercises.nativequery;

import lombok.Data;

@Data

public class StudentNativeDto {
    private Integer id;
    private String name;
    private String surname;

    public StudentNativeDto(Integer id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }
}
