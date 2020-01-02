package com.example.demo.controllers;

import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
// @RequestMapping("/xxx")
public class DateFormatController {
    @GetMapping("/date")
    @ResponseBody
    public String date(Locale locale){
        System.out.println("DateFormatController");
        return new Date().toString();
    }
}