package com.yjx.demo.quizzes.thoughtwork.service;

import com.yjx.demo.quizzes.thoughtwork.model.Ticket;

public class ProxyBuyService {

    public void proxyBuy(ByTicketCall<Ticket> callable){
        System.out.println("buy ticket");
        Ticket ticket = new Ticket();
        ticket.setTicketNo("001");
        ticket.setMsg("小明代理");
        try {
            callable.call(ticket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
