package org.japb.spacecraftservice.infrastructure.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ExceptionLogAspect {

    @AfterThrowing(pointcut = "execution(* org.japb.spacecraftservice..*(..))", throwing = "ex")
    public void logException(Exception ex) {
        log.error("Exception occurred: ", ex);
    }


}
