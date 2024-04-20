package Hibernate.exercises.orphanRemoval;

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
//        saveDate();
//        removeParent();
        setParentNull();
    }

    private static void setParentNull() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.e.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        Post post = session.get(Post.class,2L);
        post.getCommentList().remove(0);

        t.commit();
        session.close();
        factory.close();
    }

    private static void removeParent() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.e.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        Post post = session.get(Post.class,1L);
        session.delete(post);

        t.commit();
        session.close();
        factory.close();
    }

    private static void saveDate() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.e.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        Post post = new Post();
        post.setTitle("Yangi qiyshiq rasm.");
        post.setContent("Bu rasmni 'sluchayna' olingan");

        Comment comment = new Comment();
        comment.setContent("Rasmga layk bos");
        comment.setCreatedDate(LocalDateTime.now());
        comment.setPost(post);

        Comment comment2 = new Comment(); // child
        comment2.setContent("Qo'yni dumbasimi bu");
        comment2.setCreatedDate(LocalDateTime.now());
        comment2.setPost(post);

        post.setCommentList(List.of(comment, comment2));

        session.persist(post);
        t.commit();
        session.close();
        factory.close();
    }


}
