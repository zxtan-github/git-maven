package org.ifunq.tanzx.spring.DataJpa;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.persistence.EntityManager;

public class SpringDataJpaTest02 {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("spring/DATA/Spring-Data-Jpa.xml");
        LocalContainerEntityManagerFactoryBean factory = context.getBean(LocalContainerEntityManagerFactoryBean.class);
        EntityManager entityManager = factory.getNativeEntityManagerFactory().createEntityManager();
        User user1 = new User();
        user1.setId(7);
        user1.setName("tangyanming");
        user1.setAddress("hunan");
        user1.setPhone("15998989898");
        entityManager.persist(user1);

    }
}
