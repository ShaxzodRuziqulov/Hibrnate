package Hibernate.exercises.cascadeType;

import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
//    @Cascade({org.hibernate.annotations.CascadeType.REPLICATE})
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private List<Orders> ordersList;


}
