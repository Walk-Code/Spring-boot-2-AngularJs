package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DemoController {

    @RequestMapping("/")
    String home(ModelMap modelMap) {
            modelMap.addAttribute("title", "Demo");
            return "index";
    }

    @RequestMapping("partials/{page}")
    String partialHandler(@PathVariable("page") final String page) {
        return  page;
    }

}
