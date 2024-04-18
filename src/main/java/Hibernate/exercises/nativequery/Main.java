package Hibernate.exercises.nativequery;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        saveddata();
//        simpleNativeQuery();
//        partialNativeQuery();
        usingSqlResultSetMapping();

    }

    private static void usingSqlResultSetMapping() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.e.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        Query<StudentNativeDto> query = session.createNativeQuery("select s.id,s.name,s.surname from student_native s", "StudentInfoMapping");
        List<StudentNativeDto> list = query.list();
        for (StudentNativeDto s: list) {
            System.out.println(s.getId()+" "+s.getName()+" "+s.getSurname());
        }
        t.commit();
        session.close();
        factory.close();
    }

    private static void partialNativeQuery() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.e.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        Query<Object[]> query = session.createNativeQuery("select s.id, s.name, s.surname, s.createdTime from student_native s");
        List<Object[]> list = query.list();

        for (Object[] s :
                list) {
            Integer id = (Integer) s[0];
            String studentName = (String) s[1];
            String studentSurname = (String) s[2];
            LocalDateTime createdDate = ((Timestamp) s[3]).toLocalDateTime();
            System.out.println(id + " " + studentName + " " + studentSurname + " " + createdDate);
        }
        t.commit();
        session.close();
        factory.close();
    }

    private static void simpleNativeQuery() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.e.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        Query<StudentNative> query = session.createNativeQuery("select * from student_native", StudentNative.class);
        List<StudentNative> list = query.list();
        for (StudentNative s :
                list) {
            System.out.println(s.getId() + "-" + s.getName() + "-" + s.getSurName() + "-" + s.getCreatedTime());
        }
        t.commit();
        session.close();
        factory.close();

    }

    private static void saveddata() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.e.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        StudentNative studentNative = new StudentNative();
        studentNative.setName("Shaxzod");
        studentNative.setSurName("Ruziqo'lov");
        studentNative.setCreatedTime(LocalDateTime.now());
        session.save(studentNative);

        StudentNative studentNative1 = new StudentNative();
        studentNative1.setName("Saodat");
        studentNative1.setSurName("Ruziqo'lova");
        studentNative1.setCreatedTime(LocalDateTime.now());
        session.save(studentNative1);
        t.commit();
        session.close();
        factory.close();
    }
}
