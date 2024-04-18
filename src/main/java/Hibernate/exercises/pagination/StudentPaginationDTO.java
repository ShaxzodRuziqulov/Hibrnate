package Hibernate.exercises.pagination;

import lombok.Data;

@Data
public class StudentPaginationDTO {
    private Long id;
    private String name;
    private String surname;

    public StudentPaginationDTO(Long id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }
}
