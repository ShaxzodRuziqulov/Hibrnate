package Hibernate.exercises.caching;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.e.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        StudentCaching studentCaching = new StudentCaching();
        studentCaching.setName("Shaxzod");
        studentCaching.setSurname("Ruziqulov");
        studentCaching.setCreatedDate(LocalDateTime.now());
        session.save(studentCaching);

        Thread.sleep(3000);
        StudentCaching studentCaching1 = session.get(StudentCaching.class,studentCaching.getId());

        Session session1 = factory.openSession();
        StudentCaching studentCaching2 = session1.get(StudentCaching.class,studentCaching.getId());

        session.close();
        factory.close();

    }
}
