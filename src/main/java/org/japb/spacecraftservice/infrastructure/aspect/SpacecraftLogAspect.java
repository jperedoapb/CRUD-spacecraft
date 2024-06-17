package org.japb.spacecraftservice.infrastructure.aspect;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Log4j2
public class SpacecraftLogAspect {
    @Before("execution(* org.japb.spacecraftservice.application.service.SpacecraftServiceImpl.getSpacecraftById(..)) && args(id)")
    public void logIfIdIsNegative(Long id) {
        if (id < 0) {
            log.warn("Attempted to retrieve a spacecraft with a negative ID: " + id);
        }
    }

    @Before("execution(* org.japb.spacecraftservice.application.service.CharacterServiceImpl.getCharactersBySpacecraft(..)) && args(spacecraftId)")
    public void logIfNegativeSpacecraftId(Long spacecraftId) {
        if (spacecraftId < 0) {
            log.error("Attempted to retrieve characters with a negative spacecraft ID: " + spacecraftId);
        }
    }

}
