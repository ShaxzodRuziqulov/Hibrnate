package Hibernate.exercises.namedquery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        saveData();
        namedQuery();
        nativeNamedQueryExample();
    }

    private static void nativeNamedQueryExample() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.e.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();

        Query<StudentNamedQuery> query = session.createNamedQuery("findAllStudentByNativeQuery");

        query.setParameter("name", "Shaxzod");
        List<StudentNamedQuery> list = query.list();
        for (StudentNamedQuery s :
                list) {
            System.out.println(s);
        }
        session.close();
        factory.close();
    }

    private static void namedQuery() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.e.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();

        Query<StudentNamedQuery> query = session.createNamedQuery("findAllStudentByName");
        query.setParameter("name", "Shaxzod");
        List<StudentNamedQuery> list = query.list();

        for (StudentNamedQuery s :
                list) {
            System.out.println(s);
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

        StudentNamedQuery student = new StudentNamedQuery();
        student.setName("Shaxzod");
        student.setSurname("Ruziqulov");
        student.setCreatedDate(LocalDateTime.now());
        session.save(student);

        StudentNamedQuery student1 = new StudentNamedQuery();
        student1.setName("Bahodir");
        student1.setSurname("Ruziqulov");
        student1.setCreatedDate(LocalDateTime.now());
        session.save(student1);

        t.commit();
        session.close();
        factory.close();
    }
}
