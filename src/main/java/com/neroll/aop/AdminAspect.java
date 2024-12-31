package com.neroll.aop;

import cn.dev33.satoken.stp.StpUtil;
import com.neroll.pojo.Result;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AdminAspect {

    @Pointcut("execution(* com.neroll.controller.*.*(..))")
    public void checkLoginPointCut() {

    }

    @Pointcut("execution(* com.neroll.controller.UserController.login(..))")
    public void userLoginPointCut() {

    }

    @Around(value = "checkLoginPointCut() && !userLoginPointCut()")
    public Object loginCheck(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("AOP...");
        if (!StpUtil.isLogin())
            return Result.error("未登录");
        return joinPoint.proceed();
    }
}
