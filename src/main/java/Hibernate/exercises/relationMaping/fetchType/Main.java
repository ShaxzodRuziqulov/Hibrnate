package Hibernate.exercises.relationMaping.fetchType;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class Main {
    public static void main(String[] args) {
//        saveDate();
//        eagerExample();
        lazyExample();
//        lazyButGetAllDateExample();
//        throwsException();
    }

    public static void saveDate() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("persistence.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        PersonEntity person = new PersonEntity();
        person.setName("Alish");
        person.setSurname("Aliyev");
        session.save(person);

        CarEntity car1 = new CarEntity();
        car1.setModel("Lacetti");
        car1.setColor("White");
        car1.setPerson(person);
        session.save(car1);

        CarEntity car2 = new CarEntity();
        car2.setModel("Tico");
        car2.setColor("Black");
        car2.setPerson(person);
        session.save(car2);

        CarEntity car3 = new CarEntity();
        car3.setModel("Damas");
        car3.setColor("Red");
        car3.setPerson(person);
        session.save(car3);

        t.commit();

        factory.close();
        session.close();
    }

    public static void eagerExample() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("persistence.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();

        PersonEntity person = session.get(PersonEntity.class, 1);

        factory.close();
        session.close();
    }

    public static void lazyExample() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("persistence.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();

        PersonEntity person = session.get(PersonEntity.class, 1);
        List<CarEntity> carList = person.getCarList();
        for (CarEntity car : carList) {
            System.out.println(car);
        }

        factory.close();
        session.close();
    }

    public static void lazyButGetAllDateExample() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("persistence.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();

        PersonEntity person = session.get(PersonEntity.class, 1);

        session = factory.openSession();
        List<CarEntity> carList = person.getCarList();
        for (CarEntity car : carList) {
            System.out.println(car);
        }
        factory.close();
        session.close();
    }

    public static void throwsException() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("persistence.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();

        PersonEntity person = session.get(PersonEntity.class, 1);
        session.close();

        session = factory.openSession();
        List<CarEntity> carList = person.getCarList();
        for (CarEntity car : carList) {
            System.out.println(car);
        }
        factory.close();
        session.close();
    }
}
