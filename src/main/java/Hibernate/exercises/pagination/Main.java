package Hibernate.exercises.pagination;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        saveData();
        paginationExample();
        paginationExampleNative();
    }

    private static void paginationExampleNative() {
        // Ma'lumotlar ombori bilan bog'lanish
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.e.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        // Ma'lumotlar omboridan fabrikani olish
        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();

        // Native SQL so'rovni yaratish
        Query<StudentPagination> query = session.createNativeQuery(
                "SELECT * FROM student_pagination s ORDER BY s.createdtime LIMIT :limitNum OFFSET :offsetNum",
                StudentPagination.class
        );

        // Parameterlarni o'rnatish
        query.setParameter("offsetNum", 3); // Offsetni o'rnatish
        query.setParameter("limitNum", 5); // Limitni o'rnatish

        // Natijalarni olish
        List<StudentPagination> list = query.list();

        // Natijalarni chiqarish
        for (StudentPagination student : list) {
            System.out.println(student.getId() + " " + student.getName() + " " + student.getSurname() + " " + student.getCreatedTime());
        }

        // Ma'lumotlar omborini yopish
        session.close();
        factory.close();
    }


    private static void paginationExample() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.e.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();

        Query<StudentPagination> query = session.createQuery("from StudentPagination s order by s.id", StudentPagination.class);
        query.setMaxResults(3);
        query.setFirstResult(5);
        List<StudentPagination> list = query.list();
        for (StudentPagination s : list) {
            System.out.println(s.getId() + "-" + s.getName() + "-" + s.getSurname() + "-" + s.getCreatedTime());
        }
        session.close();
        factory.close();
    }

    private static void saveData() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.e.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        StudentPagination studentPagination = new StudentPagination();
        studentPagination.setName("Shaxzod");
        studentPagination.setSurname("Ruziqulov");
        studentPagination.setCreatedTime(LocalDateTime.now());
        session.save(studentPagination);

        StudentPagination studentPagination1 = new StudentPagination();
        studentPagination1.setName("Bahodir");
        studentPagination1.setSurname("Ruziqulov");
        studentPagination1.setCreatedTime(LocalDateTime.now());
        session.save(studentPagination1);
        StudentPagination student1 = new StudentPagination();
        student1.setName("Ali");
        student1.setSurname("Aliyev");
        student1.setCreatedTime(LocalDateTime.now());
        session.save(student1);

        StudentPagination student2 = new StudentPagination();
        student2.setName("Valish");
        student2.setSurname("Valiyev");
        student2.setCreatedTime(LocalDateTime.now());
        session.save(student2);

        StudentPagination student3 = new StudentPagination();
        student3.setName("Toshmat");
        student3.setSurname("Toshmatov");
        student3.setCreatedTime(LocalDateTime.now());
        session.save(student3);

        StudentPagination student4 = new StudentPagination();
        student4.setName("Eshmat");
        student4.setSurname("Eshmat");
        student4.setCreatedTime(LocalDateTime.now());
        session.save(student4);
        t.commit();
        session.close();
        factory.close();
    }
}
