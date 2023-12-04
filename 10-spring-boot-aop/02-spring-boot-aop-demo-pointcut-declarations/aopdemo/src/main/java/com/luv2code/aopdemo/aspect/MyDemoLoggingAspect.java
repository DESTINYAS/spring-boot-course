package com.luv2code.aopdemo.aspect;

import com.luv2code.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

    //add a new advice for @AfterReturning on the findAccounts method

    @Around("execution(* com.luv2code.aopdemo.service.*.getFortune(..))")
    public Object aroundGetFortune(
            ProceedingJoinPoint proceedingJoinPoint
    )throws Throwable{
        //print out method we are advicing on
        String method = proceedingJoinPoint.getSignature().toShortString();
        System.out.println("\n====>Executing @Around on method: "+method);
        //get begin timestamp
        long begin=System.currentTimeMillis();
        //execute the method
        Object result=null;
        try {
           result = proceedingJoinPoint.proceed();
        }catch (Exception exc){
            //log the exception
            System.out.println(exc.getMessage());

            //rethrow exc
            throw exc;

            //give user a custom message
            // result="Major accident! no worries private helicopter is coming";
        }
        //get end timestamp
        long end =System.currentTimeMillis();
        //compute duration and display it
        long duration= end-begin;
        System.out.println("\n====>Duration: "+duration/1000.0+" seconds");
        return result;
    }
    @After("execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))")
    public void afterFinallyFindAccountAdvice(JoinPoint joinPoint){

        //print out which method we are advising on
        String method = joinPoint.getSignature().toShortString();
        System.out.println("\n====>Executing @After (finally) on method: "+method);

    }
    @AfterThrowing(
            pointcut="execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
            throwing="exc"
    )
    public void afterThrowingFindAccountsAdvice( JoinPoint joinPoint,Throwable exc){
        //print out which method we are advising on
        String method = joinPoint.getSignature().toShortString();
        System.out.println("\n====>Executing @AfterThrowinging on method: "+method);
        //log exception
        System.out.println("\n====>The exception is: "+exc);
    }
    @AfterReturning(
        pointcut="execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
        returning="result"
    )
    public void afterReturningFindAccountAdvice(JoinPoint joinPoint, List<Account>result){
        //print out which method we are advising on
        String method = joinPoint.getSignature().toShortString();
        System.out.println("\n====>Executing @AfterReturning on method: "+method);
        //print out the results of the method call
        System.out.println("\n=====> result is: "+result);

        //let's post process the data ...modify it
        //convert account name to uppercase
        convertAccountNameToUpperCase(result);
        System.out.println("\n=====> result is: "+result);
    }

    private void convertAccountNameToUpperCase(List<Account> result) {
        //loop through accounts
        for (Account account : result) {
            //get uppercase version of name
            String upperAccountName = account.getName().toUpperCase();
            //update the name on the account
            account.setName(upperAccountName);
        }
    }
    @Before("com.luv2code.aopdemo.aspect.AopExpressions.forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice(JoinPoint joinPoint){
        System.out.println("\n=====>Executing @Before advice on addAccount()");

        //display method signature
        MethodSignature methodSignature=(MethodSignature) joinPoint.getSignature();
        System.out.println("Method: "+methodSignature);
        //display method arguments
        //get args
        Object[] args= joinPoint.getArgs();
        //loop through args
        for (Object arg:args){
            System.out.println(arg);
            if (arg instanceof Account){
                //downcast and print account specific stuff
                Account account=(Account) arg;
                System.out.println("Account name: "+account.getName());
                System.out.println("Account level: "+account.getLevel());
            }
        }
    }


}
