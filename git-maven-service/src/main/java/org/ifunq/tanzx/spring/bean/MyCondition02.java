package org.ifunq.tanzx.spring.bean;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class MyCondition02 implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        if ("tanzongxi-maven-project".equals(conditionContext.getEnvironment().getProperty("appName"))) {
            return true;
        } else {
            return false;
        }
    }
}
