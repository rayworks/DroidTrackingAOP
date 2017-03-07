package com.rayworks.tracker;

import com.rayworks.tracker.annotation.TargetScope;
import com.rayworks.tracker.annotation.Trackable;
import com.rayworks.tracker.utils.DroidLogger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sean on 3/6/17.
 * <p>
 * Aspect representing the cross cutting-concern for tracking: Method
 */

@Aspect
public class TraceAspect {

    private static final String POINTCUT_METHOD =
            "execution(@com.rayworks.tracker.annotation.Trackable * *(..))";

    @Pointcut(POINTCUT_METHOD)
    public void methodAnnotatedWithTrace() {
    }

    @Around("methodAnnotatedWithTrace()")
    public Object weaveJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();

        Method method = methodSignature.getMethod();
        DroidLogger.d("method : " + methodName);

        Object[] args = joinPoint.getArgs();

        // recognize the params via enum
        //http://stackoverflow.com/questions/10581324/aspectj-retrieve-list-of-annotated-parameters
        if (DroidLogger.isDebugMode()) {
            for (Object obj : args) {
                if (obj instanceof HashMap) {
                    HashMap<String, Object> mp = (HashMap<String, Object>) obj;

                    DroidLogger.d(">>> tracking map :");
                    for (String k : mp.keySet()) {
                        DroidLogger.d("elem " + k + ":" + mp.get(k));
                    }
                }
            }
        }

        Trackable annotation = getTrackableAnnotation(method);
        DroidLogger.d("Type annotation : " + annotation);

        Object result = joinPoint.proceed();

        if (annotation.value().equals(TargetScope.STATE)) {
            // parameter contract
            if (args.length == 2 && args[0] instanceof String && args[1] instanceof Map) {
                DroidLogger.d(">>> tracking for " + args[0]);
                DroidTrackerImpl.getTracker().trackState((String) args[0], (HashMap<String, Object>) args[1]);
            }
        }

        return result;
    }

    private Trackable getTrackableAnnotation(Method method) {
        if (method.isAnnotationPresent(Trackable.class)) {
            return method.getAnnotation(Trackable.class);
        } else {
            throw new IllegalArgumentException("Trackable annotation not found.");
        }
    }
}
