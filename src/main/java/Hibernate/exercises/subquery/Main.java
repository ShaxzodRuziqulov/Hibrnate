package Hibernate.exercises.subquery;

import org.hibernate.Query;
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
//        createData();
        subqueryEx();
        subqueryEx1();
    }

    private static void subqueryEx() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.e.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        // Ma'lumotlar omboridan fabrikani olish
        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        String hql = "FROM Cat AS fatcat " +
                "WHERE fatcat.weight > (" +
                "    SELECT AVG(cat.weight) FROM DomesticCat AS cat" +
                ")";
        Query<Cat> query = session.createQuery(hql, Cat.class);
        List<Cat> result = query.getResultList();

        for (Cat c : result) {
            System.out.println(c.getName() + " " + c.getWeight());
        }
        session.close();
        factory.close();
    }
    private static void subqueryEx1() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.e.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        // Ma'lumotlar omboridan fabrikani olish
        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        String hql = "from DomesticCat as wc\n" +
                "where wc.name = some (\n" +
                "    select cat.name from Cat as cat\n" +
                ")";
        Query<DomesticCat> query = session.createQuery(hql, DomesticCat.class);
        List<DomesticCat> result = query.getResultList();

        for (DomesticCat d : result) {
            System.out.println(d.getName() + " " + d.getWeight());
        }
        session.close();
        factory.close();
    }

    private static void createData() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.e.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        // Ma'lumotlar omboridan fabrikani olish
        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        Cat cat = new Cat();
        cat.setName("pishak");
        cat.setMate(true);
        cat.setWeight(8.0);
        session.save(cat);

        DomesticCat domesticCat = new DomesticCat();
        domesticCat.setName("pishak-1");
        domesticCat.setWeight(3.0);
        session.save(domesticCat);

        t.commit();
        session.close();
        factory.close();
    }
}
