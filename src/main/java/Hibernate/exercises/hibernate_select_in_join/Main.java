package Hibernate.exercises.hibernate_select_in_join;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        saveData();
//        simpleJoinSelect();
//        selectUsingNonEntityConstructor();
    }

    private static void selectUsingNonEntityConstructor() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.e.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();


        Query<StudentAddressInfoDTO> query = session.createQuery("select new Hibernate.exercises.hibernate_select_in_join.StudentAddressInfoDTO(s.id,s.name,s.surname,a.id,a.city,a.country) " +
                "  from StudentSelect s inner join s.addresses a");
        List<StudentAddressInfoDTO> list = query.list();

        for (StudentAddressInfoDTO data : list) {
            System.out.println(data.getStudentId() + " " + data.getName() + " " + data.getSurname()
                    + " " + data.getAddressId() + " " + data.getCity() + " " + data.getCountry());
        }
        t.commit();
        session.close();
        factory.close();
    }

    private static void simpleJoinSelect() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.e.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        Query query = session.createQuery("select s.id, s.name,s.surname,a.city,a.country from StudentSelect s inner join s.addresses a");
        List<Object[]> list = query.list();
        for (Object[] obj : list) {
            Long studentId = (Long) obj[0];
            String name = (String) obj[1];
            String surname = (String) obj[2];
            String city = (String) obj[3];
            String district = (String) obj[4];
            System.out.println(studentId + " " + name + " " + surname + " " + city + " " + district);
        }
        t.commit();
        session.close();
        factory.close();
    }

    private static void saveData() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.e.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        StudentSelect studentSelect = new StudentSelect();
        studentSelect.setName("Ali");
        studentSelect.setSurname("Aliyev");
        studentSelect.setPhone("99988");
        studentSelect.setBirthDate(LocalDate.of(2022, 1, 5));
        session.save(studentSelect);

        Address address = new Address();
        address.setCity("Toshkent");
        address.setCountry("Uzbekistan");
        address.setStreet("Bilmayman");
        address.setStudentSelect(studentSelect);
        session.save(address);

        t.commit();
        session.close();
        factory.close();

    }

}
