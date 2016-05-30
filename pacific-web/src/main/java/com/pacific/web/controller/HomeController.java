package com.pacific.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Fe on 16/5/27.
 */
@Controller
@RequestMapping("/")
public class HomeController {

    public static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping("/home.htm")
    public String home() {
        return "home";
    }
}
