package ru.cotarius.springcourse.springaop.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.event.Level;
import org.springframework.stereotype.Component;
import ru.cotarius.springcourse.springaop.annotations.ExecuteTime;

@Slf4j
@Component
@Aspect
public class AspectLoggingMethodExecutionTime {
    @Pointcut("within(@ru.cotarius.springcourse.springaop.annotations.ExecuteTime *)")
    public void logMethod(){}

    @Pointcut("@annotation(ru.cotarius.springcourse.springaop.annotations.ExecuteTime)")
    public void logWithAnnotation(){}

    @Around("logMethod() || logWithAnnotation()")
    public Object loggingMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        Long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        Long endTime = System.currentTimeMillis();
        Level level = extractLevel(joinPoint);

        log.atLevel(level).log("Метод {} класса {} выполнялся {} ms",
                joinPoint.getSignature().getName(),
                joinPoint.getSignature().getDeclaringType().getSimpleName(),
                endTime - startTime);

        return result;
    }

    private Level extractLevel(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        ExecuteTime annotation = methodSignature.getMethod().getAnnotation(ExecuteTime.class);

        if (annotation != null) {
            return annotation.level();
        }
        return joinPoint.getTarget().getClass().getAnnotation(ExecuteTime.class).level();
    }
}
