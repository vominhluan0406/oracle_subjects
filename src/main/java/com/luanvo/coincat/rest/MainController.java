package com.luanvo.coincat.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @GetMapping({"/","/index.html"})
    public String index(){
        return "chart";
    }

    @GetMapping("/real-time.html")
    public String realTime(){
        return "char-real-time";
    }
}
