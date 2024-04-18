package Hibernate.exercises.hibernate_select_in_join;

import lombok.Data;

@Data
public class StudentAddressInfoDTO {
    private Long studentId;
    private String name;
    private String surname;
    private Long addressId;
    private String city;
    private String country;

    public StudentAddressInfoDTO(Long studentId, String name, String surname,Long addressId,String city, String country) {
        this.studentId = studentId;
        this.name = name;
        this.surname = surname;
        this.addressId = addressId;
        this.city = city;
        this.country = country;
    }

}