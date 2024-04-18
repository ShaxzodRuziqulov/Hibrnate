package Hibernate.services;

import Hibernate.exercises.Post;
import Hibernate.exercises.StudentUUID;
import Hibernate.exercises.UserHibernate;
import Hibernate.exercises.relationMaping.onetoone.Address;
import Hibernate.exercises.relationMaping.onetoone.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.Query;
import java.util.List;

public class Service {
    public void create(StudentUUID studentUUID) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();

        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        session.save(studentUUID);
        t.commit();

        System.out.println("successfully saved");

        factory.close();
        session.close();
    }

    public void createPost(Post post) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();

        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        session.save(post);
        t.commit();

        System.out.println("successfully saved");

        factory.close();
        session.close();
    }

    public void create(UserHibernate userHibernate) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();

        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        session.save(userHibernate);
        t.commit();

        System.out.println("successfully saved");

        factory.close();
        session.close();
    }

    public static List<StudentUUID> all() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();

        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();

        Query query = session.createQuery("from StudentMultiJoin ", StudentUUID.class);
        List list = query.getResultList();
        factory.close();
        session.close();
        return list;
    }

    public static List<UserHibernate> allUser() {
        // Hibernate bilan bog'lanish uchun resurslarni yaratish
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory factory = metadata.getSessionFactoryBuilder().build();

        // Hibernate orqali sessiyani ochish
        Session session = factory.openSession();

        // HQL so'rovni yaratish
        String hql = "FROM UserHibernate s WHERE s.age = 25";

        // So'rovni bajarish uchun hazir Cat yaratish
        Query query = session.createQuery(hql);

        // Natijalarni olish
        List<UserHibernate> userList = query.getResultList();

        // Resurslarni yopish
        session.close();
        factory.close();

        // Natijalarni qaytarish
        return userList;
    }

    public static UserHibernate hqlCode(String name) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();

        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        try {
            String hql = "from UserHibernate where name=:name";
            Query query = session.createQuery(hql);
            query.setParameter("name", name);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            factory.close();
        }
        return null;
    }

    public static void oneToOne() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();

        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();

        try (factory; Session session = factory.openSession()) {
            Employee employee = new Employee();
            employee.setName("Shaxzod");
            employee.setLastName("Ruziqulov");
            employee.setAddress(employee.getAddress());

            Address address = new Address();
            address.setName("Samarqand");
            address.setEmployee(employee);
            session.save(address);
            session.save(employee);


        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public static StudentUUID getById(Long id) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();

        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();

        // 1-usul id berish
        // StudentMultiJoin student = session.get(StudentMultiJoin.class, id);

        // 2-usul
        Query query = session.createQuery("from StudentMultiJoin where id=" + id, StudentUUID.class);
        List<StudentUUID> list = query.getResultList();
        factory.close();
        session.close();

        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    public static List<StudentUUID> getByNameAndLastname(String name, String lastName) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();

        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();

        Query query = session.createQuery("from StudentUUID where name=:name and lastName=:lastName", StudentUUID.class);
        query.setParameter("name", name);
        query.setParameter("lastName", lastName);

        List<StudentUUID> list = query.getResultList();

        factory.close();
        session.close();

        return list;
    }

    public static void update(Long id, StudentUUID studentUUIDEntity) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();

        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();

        Session session = factory.openSession();
//        1-usuli - kamchiligi update qilishda barcha qatorlar update qilinadi
        StudentUUID studentUUID = session.get(StudentUUID.class, id);
        if (studentUUID == null) {
            System.out.println("xatolik bor: Id topilmadi");
        }
//        assert studentUUID != null;
//        studentUUID.setName(studentUUIDEntity.getName());
//        studentUUID.setLastName(studentUUIDEntity.getLastName());
//        studentUUID.setDate(studentUUIDEntity.getDate());

        Transaction transaction = session.beginTransaction();

        // 2-usul
        Query query = session.createQuery("update StudentUUID set name=:name,lastName=:lastName, date=:date, age=:age where id=:id");
        query.setParameter("name", studentUUIDEntity.getName());
        query.setParameter("lastName", studentUUIDEntity.getLastName());
        query.setParameter("date", studentUUIDEntity.getDate());
        query.setParameter("age", studentUUIDEntity.getAge());
        query.setParameter("id", id);

        int effectedRow = query.executeUpdate();
//        session.update(studentUUID); // session.save - ni ham update sifatida ishlatishimiz mumkin

        transaction.commit();
        session.close();
        factory.close();

    }

    public static void delete(Long id) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();

        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();

        Session session = factory.openSession();

        StudentUUID studentUUID = session.get(StudentUUID.class, id);
        if (studentUUID == null) {
            System.out.println("not fount id");
            return;
        }
        Transaction transaction = session.beginTransaction();
        session.delete(studentUUID);

        transaction.commit();
        session.close();
        factory.close();
    }

    public static void deleteUsingHql(Long id) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();

        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();

        Session session = factory.openSession();
        StudentUUID studentUUID = session.get(StudentUUID.class, id);
        if (studentUUID == null) {
            System.out.println("not fount id");
            return;
        }
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("delete from StudentMultiJoin where id=:id");
        query.setParameter("id", id);
        query.executeUpdate();

        transaction.commit();
        session.close();
        factory.close();
    }
}
