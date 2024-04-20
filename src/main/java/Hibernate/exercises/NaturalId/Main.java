package Hibernate.exercises.NaturalId;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
//        saveAndGetStudent();
//        getStudentNaturalId();
        saveAndGetSubject();
    }

    private static void saveAndGetSubject() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.e.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        SubjectNaturalIdE subjectNaturalIdE = new SubjectNaturalIdE();
        subjectNaturalIdE.setTitle("java");
        subjectNaturalIdE.setLocalCode("AOO1");
        subjectNaturalIdE.setGeneralCode("AAB");

//        session.save(subjectNaturalIdE);
//        transaction.commit();
//        session.clear();

        Optional<SubjectNaturalIdE> optional = session.byNaturalId(SubjectNaturalIdE.class)
                .using("localCode","AOO1")
                .using("generalCode","AOO1")
                .loadOptional();
        optional.ifPresent(System.out::println);
        session.close();
        factory.close();
    }

    private static void getStudentNaturalId() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.e.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        Optional<StudentNaturalIdE> optional = session.bySimpleNaturalId(StudentNaturalIdE.class).loadOptional("AB1234567");
        optional.ifPresent(System.out::println);

        transaction.commit();
        session.close();
        factory.close();
    }

    private static void saveAndGetStudent() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.e.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        StudentNaturalIdE studentNaturalIdE = new StudentNaturalIdE();
        studentNaturalIdE.setName("Shaxzod");
        studentNaturalIdE.setLastName("Ruziqulov");
        studentNaturalIdE.setPasswordId("AB1234567");

        session.save(studentNaturalIdE);
        transaction.commit();
        session.clear();

        Optional<StudentNaturalIdE> optional = session.bySimpleNaturalId(StudentNaturalIdE.class).loadOptional("AB1234567");
        optional.ifPresent(System.out::println);
//        if (optional.isPresent()){
//            System.out.println(optional.get());
//        }
        session.close();
        factory.close();
    }
}
