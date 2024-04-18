package Hibernate.exercises.cascadeType;

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
//        persist();
//        marge();
        remove();
    }

    private static void remove() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.e.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        Orders orders = new Orders();
        orders.setOrderAddress("Chilonzor");
        orders.setTotalPrice(5000d);
        orders.setItemCount(1);

        Customer customer = new Customer();
        customer.setName("Shaxzod");
        customer.setSurname("Ruziqulov");
        customer.setOrdersList(List.of(orders));

        orders.setCustomer(customer);

        session.persist(customer);
        session.flush();
        session.clear();

        Customer savedCustomer = session.load(Customer.class,customer.getId());
        session.remove(savedCustomer);

        t.commit();
        session.close();
        factory.close();
    }

    private static void marge() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.e.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        Orders orders = new Orders();
        orders.setOrderAddress("Chilonzor");
        orders.setTotalPrice(5000d);
        orders.setItemCount(1);

        Customer customer = new Customer();
        customer.setName("Shaxzod");
        customer.setSurname("Ruziqulov");
        customer.setOrdersList(List.of(orders));

        orders.setCustomer(customer);

        session.persist(customer);
        session.flush();
        session.clear();

        Customer savedCustomer = session.load(Customer.class,customer.getId());
        Orders saveOrder = session.load(Orders.class, orders.getId());
        savedCustomer.setName("Alish");
        saveOrder.setItemCount(7);

        session.update(savedCustomer);

        t.commit();
        session.close();
        factory.close();
    }

    private static void persist() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.e.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        Orders orders = new Orders();
        orders.setOrderAddress("Chilonzor");
        orders.setTotalPrice(5000d);
        orders.setItemCount(1);


        Customer customer = new Customer();
        customer.setName("Shaxzod");
        customer.setSurname("Ruziqulov");
        customer.setOrdersList(List.of(orders));

        orders.setCustomer(customer);

        session.persist(customer);
        t.commit();
        session.close();
        factory.close();
    }
}
