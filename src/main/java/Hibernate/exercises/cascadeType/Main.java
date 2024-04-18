package Hibernate.exercises.cascadeType;

import org.hibernate.ReplicationMode;
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
//        remove();
//        refresh();
//        detach();
//        all();
//        replicate();
        saveUpdate();
    }

    private static void saveUpdate() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.e.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        Orders orders = new Orders();
        orders.setOrderAddress("Toshkentdan");
        orders.setTotalPrice(2d);
        orders.setItemCount(4);

        Customer customer = new Customer();
        customer.setName("Ismi yuq_");
        customer.setSurname("Familiyasi yuq_");
        customer.setOrdersList(List.of(orders));

        orders.setCustomer(customer);
        session.save(customer);

        t.commit();
        session.close();
        factory.close();
    }

    private static void replicate() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.e.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        Customer customer = session.get(Customer.class,1L);
        Orders orders = session.get(Orders.class,1L);

        session.detach(customer);
        session.replicate(customer, ReplicationMode.OVERWRITE);

        t.commit();
        session.close();
        factory.close();
    }

    private static void all() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.e.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        Orders orders = new Orders();
        orders.setOrderAddress("Toshket=ntni qayeridir");
        orders.setTotalPrice(23d);
        orders.setItemCount(1);

        Customer customer = new Customer();
        customer.setName("Ismi yuq");
        customer.setSurname("Familiyasi yuq");
        customer.setOrdersList(List.of(orders));

        orders.setCustomer(customer);

        session.persist(customer);

        t.commit();
        session.close();
        factory.close();
    }

    private static void detach() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.e.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        Orders orders = new Orders();
        orders.setOrderAddress("Bilmadim qayerligini");
        orders.setTotalPrice(3121d);
        orders.setItemCount(3);

        Customer customer = new Customer();
        customer.setName("Shaxzod");
        customer.setSurname("Ruziqulov");
        customer.setOrdersList(List.of(orders));

        orders.setCustomer(customer);

        session.persist(customer);
        session.flush();

        session.detach(customer);
        session.get(Customer.class, customer.getId());
        session.get(Orders.class, orders.getId());

        t.commit();
        session.close();
        factory.close();
    }


    private static void refresh() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.e.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        Orders orders = new Orders();
        orders.setOrderAddress("Chilonzor");
        orders.setItemCount(11);
        orders.setTotalPrice(5000d);

        Customer customer = new Customer();
        customer.setName("Shaxzod");
        customer.setSurname("Ruziqulov");
        customer.setOrdersList(List.of(orders));

        orders.setCustomer(customer);

        session.persist(customer);
        session.flush();
        session.clear();

        customer.setName("Bahodir");
        orders.setItemCount(12);

        session.refresh(customer);
        t.commit();
        session.close();
        factory.close();
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

        Customer savedCustomer = session.load(Customer.class, customer.getId());
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

        Customer savedCustomer = session.load(Customer.class, customer.getId());
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
