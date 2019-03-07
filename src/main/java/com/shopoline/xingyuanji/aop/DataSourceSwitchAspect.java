package com.shopoline.xingyuanji.aop;

import com.shopoline.xingyuanji.config.DBTypeEnum;
import com.shopoline.xingyuanji.config.DbContextHolder;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(-100) //这是为了保证AOP在事务注解之前生效,Order的值越小,优先级越高
public class DataSourceSwitchAspect {
    private static final Logger logger = LoggerFactory.getLogger(DataSourceSwitchAspect.class);

    @Pointcut("execution(* com.shopoline.xingyuanji.service.db1..*.*(..))")
    private void db1Aspect() {
    }

    @Pointcut("execution(* com.shopoline.xingyuanji.service.db2..*.*(..))")
    private void db2Aspect() {
    }

    @Before( "db1Aspect()" )
    public void db1() {
        logger.warn("切换到db1 数据源...");
        DbContextHolder.setDbType(DBTypeEnum.db1);
    }

    @Before("db2Aspect()" )
    public void db2 () {
        logger.warn("切换到db2 数据源...");
        DbContextHolder.setDbType(DBTypeEnum.db2);
    }


}
