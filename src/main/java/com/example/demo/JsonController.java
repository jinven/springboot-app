package com.example.demo;

import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class JsonController {
	
	@RequestMapping("/json")
	public String index() {
        String message = "";
        try {
            Person man = new Person();
            man.id = 1;
            man.name = "xiaoming";
            man.money = 1000;
            man.age = 10;
            String json = JSON.toJSONString(man);
            message += "json: " + json + "<br>";
            man = JSON.parseObject(json, Person.class);
            message += "from json:" + (man!=null?"success":"fail") + "<br>";

            message += "json测试成功";
        } catch (Exception e) {
            message += "json测试失败";
            e.printStackTrace();
        }
		return message;
    }
    
    private static class Person{
        public int id;
        public String name;
        public double money;
        public short age;
    }
}
