package Hibernate.exercises;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
