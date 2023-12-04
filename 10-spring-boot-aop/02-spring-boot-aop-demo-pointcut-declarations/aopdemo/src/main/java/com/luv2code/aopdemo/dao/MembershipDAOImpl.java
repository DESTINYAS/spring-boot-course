package com.luv2code.aopdemo.dao;

import org.springframework.stereotype.Repository;


@Repository
public class MembershipDAOImpl implements MembershipDAO{
    @Override
    public void addCrazyMember() {
        System.out.println(getClass()+" :Doing some stuff as a member");
    }

    @Override
    public void goToSleep() {
        System.out.println(getClass()+": goToSleep()");
    }
}
