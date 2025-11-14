package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class HomeController {
    @RequestMapping("/")
    public String index(){
        String getView=getViewIndex();
        return getView;
    }
    public String getViewIndex(){
        return "index" ;
    }
}
