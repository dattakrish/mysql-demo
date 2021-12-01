package com.kd.personal.mysql.service;

public class ServiceClass {
    private Network network;
    private UserDao userDao;

    public String add(){

        String fromUserDao = userDao.add();
        String fromNetwork = network.add();
        return fromUserDao+fromNetwork;
    }
}
