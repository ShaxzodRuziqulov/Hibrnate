package Hibernate.exercises.hibernate_multy_join_example;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        saveData();
        getStudentCourseList();


    }

    private static void getStudentCourseList() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.e.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        Query<Object[]> query = session.createQuery("select sc.id,sc.score,sc.joinedTime ," +
                "s.id,s.name, s.surname ," +
                "c.id,c.title,c.price " +
                "from StudentCourse " +
                "sc inner join sc.studentMultiJoin " +
                "s inner join sc.course c");
        List<Object[]> list = query.list();

        for (Object[] o:list) {
            Long id = (Long) o[0];
            Double score = (Double) o[1];
            LocalDateTime joinedTime = (LocalDateTime) o[2];

            Long sId = (Long) o[3];
            String name = (String) o[4];
            String surname = (String) o[5];

            Long cId = (Long) o[6];
            String title = (String) o[7];
            Double price = (Double) o[8];

            System.out.println(id + " " + score + " " + joinedTime + " " + sId + " " + name + " " + surname + " " + cId + " " + title + " " + price);

        }
        t.commit();
        session.close();
        factory.close();
    }

    private static void saveData() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.e.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        StudentMultiJoin studentMultiJoin = new StudentMultiJoin();
        studentMultiJoin.setName("Shaxzod");
        studentMultiJoin.setSurname("Ruziqulov");
        session.save(studentMultiJoin);

        StudentMultiJoin studentMultiJoin1 = new StudentMultiJoin();
        studentMultiJoin1.setName("Bahodir");
        studentMultiJoin1.setSurname("Ruziqulov");
        session.save(studentMultiJoin1);

        StudentMultiJoin studentMultiJoin2 = new StudentMultiJoin();
        studentMultiJoin2.setName("Suxrob");
        studentMultiJoin2.setSurname("Ruziqulov");
        session.save(studentMultiJoin2);

        Course course = new Course();
        course.setTitle("qishloqda mehnat");
        course.setPrice(0.0);
        session.save(course);

        Course course1 = new Course();
        course1.setTitle("qishloqda mehnat 2");
        course1.setPrice(0.0);
        session.save(course1);

        StudentCourse studentCourse = new StudentCourse();
        studentCourse.setStudentMultiJoin(studentMultiJoin);
        studentCourse.setCourse(course);
        studentCourse.setJoinedTime(LocalDateTime.now());
        studentCourse.setScore(5.0);
        session.save(studentCourse);

        StudentCourse studentCourse1 = new StudentCourse();
        studentCourse1.setStudentMultiJoin(studentMultiJoin1);
        studentCourse1.setCourse(course1);
        studentCourse1.setJoinedTime(LocalDateTime.now());
        studentCourse1.setScore(5.0);
        session.save(studentCourse1);

        StudentCourse studentCourse2 = new StudentCourse();
        studentCourse2.setStudentMultiJoin(studentMultiJoin2);
        studentCourse2.setCourse(course1);
        studentCourse2.setJoinedTime(LocalDateTime.now());
        studentCourse2.setScore(5.0);
        session.save(studentCourse2);

        t.commit();
        session.close();
        factory.close();
    }
}
