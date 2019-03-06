package com.shopoline.xingyuanji.aop;


import com.shopoline.xingyuanji.Constants;
import com.shopoline.xingyuanji.common.ExceptionEnum;
import com.shopoline.xingyuanji.common.JsonResult;
import com.shopoline.xingyuanji.utils.RedisUtil;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * 拦截器：记录用户操作日志，检查用户是否登录……
 * @author Wuty
 */
@Aspect
@Component
public class ControllerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(ControllerInterceptor.class);

    /**
     * 定义拦截规则：拦截controller包下面的所有类中，有@RequestMapping注解的方法。
     */
    @Pointcut("execution(* com.shopoline.xingyuanji.controller..*(..)) && @annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void controllerMethodPointcut(){}

    /**
     * 拦截器具体实现
     * @param pjp
     * @return JsonResult（被拦截方法的执行结果，或需要登录的错误提示。）
     */
    // 指定拦截器规则；也可以直接把“execution(* com.xjj.........)”写进这里
    @Around("controllerMethodPointcut()")
    public Object Interceptor(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        // 获取被拦截的方法
        Method method = signature.getMethod();
        // 获取被拦截的方法名
        String methodName = method.getName();
        Object[] args = pjp.getArgs();
        Object result = null;

        if (!Constants.IGNORE_METHOD.contains(methodName)){
            if (args == null || StringUtils.isEmpty(args[Constants.LIST_SIZE_ZERO].toString())){

                JsonResult<String> json = new JsonResult<>();
                json.setState(ExceptionEnum.getKeyByValue(ExceptionEnum.EXCEPTION_2.getDesc()));
                json.setMessage(ExceptionEnum.getValueByKey(json.getState()));
                logger.warn("<-AOP->："+json.getMessage()+"\tSTATE："+json.getState()+"\tTICKET：NULL"+
                        "\tDATE："+new Date());
                result = json;
            }else{
                String token = RedisUtil.getValue(args[Constants.LIST_SIZE_ZERO].toString());
                if(token == null || token.equals("")){

                    JsonResult<String> json = new JsonResult<>();
                    json.setState(ExceptionEnum.getKeyByValue(ExceptionEnum.EXCEPTION_2.getDesc()));
                    json.setMessage(ExceptionEnum.getValueByKey(json.getState()));
                    logger.warn("<-AOP->："+json.getMessage()+"\tSTATE："+json.getState()+"\tTICKET："+args[Constants.LIST_SIZE_ZERO].toString()+
                            "\tDATE："+new Date());
                    result = json;
                }
            }
        }

        if(result == null){
            // 一切正常的情况下，继续执行被拦截的方法
            result = pjp.proceed();
        }
        return result;
    }
}

