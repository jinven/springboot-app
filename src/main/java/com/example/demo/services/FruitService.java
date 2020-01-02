package com.example.demo.services;

import com.example.demo.impls.Apple;
import com.example.demo.impls.GinSeng;
import com.example.demo.interfaces.Fruit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FruitService{
    @Autowired
    private Apple apple;
    @Autowired
    private GinSeng ginseng;
    @Bean(name="getApple")
    public Fruit<?> getApple(){
        System.out.println(apple.getClass().getName().hashCode());
        System.out.println(ginseng.getClass().getName().hashCode());
        return new Apple();
    }
}