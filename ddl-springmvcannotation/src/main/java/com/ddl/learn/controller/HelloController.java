package com.ddl.learn.controller;


import com.ddl.learn.Blue;
import com.ddl.learn.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @Autowired
    HelloService helloService;

    @Autowired
    Blue blue;

    @ResponseBody
    @RequestMapping("/hello")
    public String hello() {
        System.out.println("开始了——————————————————————————"+blue);
        String hello = helloService.sayHello("tomcat..");
        return hello;
    }

    //  /WEB-INF/views/success.jsp
    @RequestMapping("/suc")
    public String success() {
        return "success";
    }


}