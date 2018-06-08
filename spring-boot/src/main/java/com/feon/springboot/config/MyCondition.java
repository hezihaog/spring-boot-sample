package com.feon.springboot.config;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class MyCondition implements Condition {
    @Override
    public boolean matches(ConditionContext ctx, AnnotatedTypeMetadata metadata) {
        Resource res = ctx.getResourceLoader().getResource("application.yml");
        Environment env = ctx.getEnvironment();
        return res.exists() && env.containsProperty("server.servlet.context-path");
    }
}
