package Hibernate.exercises.uuid;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "UUID")
public class UUIDEntity {
    @Id
    private UUID id;
}
