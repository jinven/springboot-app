package com.example.demo.services;

import com.example.demo.interfaces.IMessageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessagePrinterService{
    final private IMessageService service;

    @Autowired
    public MessagePrinterService(IMessageService service){
        this.service = service;
    }

    public void printMessage(){
        System.out.println(this.service.getMessage());
    }
}