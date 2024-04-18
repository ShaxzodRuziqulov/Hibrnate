package Hibernate.controller;

import Hibernate.exercises.UserHibernate;
import Hibernate.services.Service;

import java.util.List;


public class Controller {
    public static void start() {
        Service service = new Service();
        {
            //create
//        newStudent s1 = new StudentMultiJoin();
//        s1.setName("Suxrob");
//        s1.setLastName("Ruziqulov");
//        s1.setAge(19L);
//        s1.setDate(LocalDateTime.now());
//        service.create(s1);
//        //CREATE POST
//        Post post = new Post();
//        post.setName("Assalomu alaykum");
//        service.createPost(post);
//        //create UserHibernate
            UserHibernate userHibernate = new UserHibernate();
            userHibernate.setName("Shaxzod");
            userHibernate.setLastName("Ruzikulov");
            userHibernate.setAge(25L);
//            service.create(userHibernate);
        }
        {
//        //get all
//        List<StudentMultiJoin> list = Service.all();
//        for (StudentMultiJoin student : list) {
//            System.out.println(student);
//        }
//

            List<UserHibernate> listUser=Service.allUser();
            for (UserHibernate u:
                 listUser) {
                System.out.println(u);
            }


        }
        {
//        StudentMultiJoin student = Service.getById(2L);
//        System.out.println(student);
//
            UserHibernate userHibernate = Service.hqlCode("Shaxzod");
//            System.out.println(userHibernate);
//        //getByNameAndLastname
//        List<StudentMultiJoin> listNameLastName = Service.getByNameAndLastname("Shaxzod", "Ruziqulov");
//        for (StudentMultiJoin studentNameLastName : listNameLastName) {
//            System.out.println(studentNameLastName);
//        }
        }
//        //update
//        StudentMultiJoin studentUpdate = new StudentMultiJoin();
//        assert student != null;
//        studentUpdate.setName("Saodat");
//        studentUpdate.setLastName("Ruziqulova");
//        studentUpdate.setDate(LocalDateTime.now());
//        studentUpdate.setAge(16L);
//        Service.update(4L, student);
//        //delete
//        Service.delete(5L);
//        Service.deleteUsingHql(5L);


        Service.oneToOne();
    }
}
