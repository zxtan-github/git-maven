package org.ifunq.tanzx.JPA;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class SpringDataJpaTest01 {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("spring/JPA/Spring-Data-Jpa.xml");
        UserRepository uerRepository = context.getBean(UserRepository.class);
        List<User> users = uerRepository.findByName("tanzongxi");
        System.out.println(users);
        List<User> users1 = uerRepository.findAll();
        System.out.println(users1);

        // @Query下HQL模式
        User user = uerRepository.queryUser("tanchengyu");
        System.out.println(user);

        // @Query下本地SQL模式
        System.out.println(uerRepository.queryUserCount());

        // UserCrudRepository
        UserCrudRepository userCrudRepository = context.getBean(UserCrudRepository.class);
        User user1 = new User();
        user1.setId(4);
        user1.setName("tangyan");
        user1.setAddress("hunan");
        user1.setPhone("18625696324");
        userCrudRepository.save(user1);
    }
}
