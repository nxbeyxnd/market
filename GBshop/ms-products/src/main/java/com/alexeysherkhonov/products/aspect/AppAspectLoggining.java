package com.alexeysherkhonov.products.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AppAspectLoggining {

    @Before("execution(public * com.alexeysherkhonov.products.controllers.ProductsController.*(..))")
    public void beforeAnyMethodWithArgs(JoinPoint joinPoint){
        System.out.println("Signature: "+ joinPoint.getSignature());
        Object[] args = joinPoint.getArgs();
        if (args.length > 0) {
            System.out.println("Args:");
            for (Object o : args) {
                System.out.println(o);
            }
        }

    }
}
