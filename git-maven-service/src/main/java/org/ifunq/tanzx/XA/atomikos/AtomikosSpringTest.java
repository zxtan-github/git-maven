package org.ifunq.tanzx.XA.atomikos;

import org.ifunq.tanzx.spring.Atomikos.Mapper1.ScTestMapper;
import org.ifunq.tanzx.spring.Atomikos.Mapper2.ScBrandMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

public class AtomikosSpringTest {
    public static void main(String[] args) {
        // 方法重写
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("spring/Atomikos/AtomikosSpring.xml");
        AtomikosSpringService service = (AtomikosSpringService) context.getBean("atomikosSpringService");
        service.doService();
    }
}
class AtomikosSpringService {
    @Autowired
    private ScBrandMapper scBrandMapper;
    @Autowired
    private ScTestMapper scTestMapper;

    @Transactional
    public void doService() {
        scBrandMapper.update("1", "TTT");
        scTestMapper.insert(3, "6",  1);
    }
}
