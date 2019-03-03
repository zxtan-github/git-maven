package org.ifunq.tanzx.spring.bean;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class MyCondition03 implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        if ("tanchengyu".equals(conditionContext.getEnvironment().getProperty("author"))) {
            return true;
        } else {
            return false;
        }
    }
}
