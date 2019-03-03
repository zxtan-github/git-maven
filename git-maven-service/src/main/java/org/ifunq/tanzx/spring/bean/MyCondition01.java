package org.ifunq.tanzx.spring.bean;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class MyCondition01 implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        SpringBean001 springBean001 = conditionContext.getBeanFactory().getBean(SpringBean001.class);
        if ("tanzongxi".equals(springBean001.getName())) {
            return true;
        } else {
            return false;
        }
    }
}
