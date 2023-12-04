package com.luv2code.aopdemo.dao;

import com.luv2code.aopdemo.Account;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class AccountDAOImpl implements AccountDAO{

    private String name;
    private String serviceCode;

    @Override
    public List<Account> findAccounts() {
        return findAccounts(false);
    }

    @Override
    public List<Account> findAccounts(boolean tripWire) {
        //for academic purpose ...simulate an exception
        if (tripWire){throw new RuntimeException("No food for yoy");}
        List<Account> accounts=new ArrayList<>();
        //create sample accounts
        Account account1=new Account("Rooney","United");
        Account account2=new Account("Arteta","Arsenal");
        Account account3=new Account("Drogba","Chelsea");
        //add them to our accounts list
        accounts.add(account1);
        accounts.add(account2);
        accounts.add(account3);
        return accounts;
    }

    @Override
    public void addAccount(Account account,boolean vipFlag) {
        System.out.println(getClass()+" : Adding an account");
    }

    @Override
    public boolean doWork() {
        System.out.println(getClass()+" : doWork()");
        return false;
    }

    public String getName() {
        System.out.println(getClass()+" : getName()");
        return name;
    }

    public void setName(String name) {
        System.out.println(getClass()+" : setName()");
        this.name = name;
    }

    public String getServiceCode() {
        System.out.println(getClass()+" : getServiceCode()");
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        System.out.println(getClass()+" : setServiceCode()");
        this.serviceCode = serviceCode;
    }
}
