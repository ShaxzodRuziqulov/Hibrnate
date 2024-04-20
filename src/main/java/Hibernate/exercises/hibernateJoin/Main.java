package Hibernate.exercises.hibernateJoin;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.List;

public class Main {
    public static void main(String[] args) {
//        saveDate();
//
//        leftJoinAsFetchExample();
//        leftJoinExample();
//        fetchJoinExample();

    }

    private static void leftJoinAsFetchExample() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hql.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        Query query = session.createQuery("select d,e from DepartmentEntity d left join d.employeeList e");
        List<Object[]> list = query.list();
        for (Object[] obj : list) {
            System.out.println(obj[0]); // get Department
            System.out.println(obj[1]); // get Employee
        }
        t.commit();
        session.close();
        factory.close();

    }

    private static void fetchJoinExample() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hql.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        Query query = session.createQuery("SELECT d From DepartmentEntity d join fetch d.employeeList");
        List<DepartmentEntity> list = query.list();
        for (DepartmentEntity department : list) {
            System.out.println(department);
        }
        t.commit();
        session.close();
        factory.close();
    }

    public static void leftJoinExample() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hql.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        Query query = session.createQuery("select d from DepartmentEntity d left join d.employeeList e");
        List<DepartmentEntity> list = query.list();
        for (DepartmentEntity department : list) {
            System.out.println(department);
        }
        t.commit();
        session.close();
        factory.close();
    }


    public static void saveDate() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hql.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        DepartmentEntity it = new DepartmentEntity();
        it.setName("IT");
        session.save(it);

        DepartmentEntity accounting = new DepartmentEntity();
        accounting.setName("Accounting");
        session.save(accounting);

        // employee
        EmployeeEntity employee1 = new EmployeeEntity();
        employee1.setName("Alish");
        employee1.setSurname("Aliyev");
        employee1.setDepartment(it);
        session.save(employee1);

        EmployeeEntity employee2 = new EmployeeEntity();
        employee2.setName("Valish");
        employee2.setSurname("Valiyev");
        employee2.setDepartment(it);
        session.save(employee2);

        EmployeeEntity employee3 = new EmployeeEntity();
        employee3.setName("Toshmat");
        employee3.setSurname("Toshmatov");
        employee3.setDepartment(accounting);
        session.save(employee3);

        EmployeeEntity employee4 = new EmployeeEntity();
        employee4.setName("Eshmat");
        employee4.setSurname("Eshmatov");
        employee4.setDepartment(it);
        session.save(employee4);

        t.commit();

        factory.close();
        session.close();
    }
}