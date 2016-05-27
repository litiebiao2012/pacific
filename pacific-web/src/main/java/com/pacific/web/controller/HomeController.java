package com.pacific.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Fe on 16/5/27.
 */
@Controller
@RequestMapping("/")
public class HomeController {

    @RequestMapping("/home.htm")
    public String home() {
        return "home";
    }
}
