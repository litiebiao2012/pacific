package com.pacific.web.controller;

import com.pacific.common.annotation.LoginCheckAnnotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @LoginCheckAnnotation(checked = false)
    @RequestMapping(value = "/login.htm",method = RequestMethod.GET)
    public String login() {
        return "login";

    }

    @LoginCheckAnnotation(checked = false)
    @RequestMapping(value = "/check.htm",method = RequestMethod.GET)
    public void check() {
        try {
            int i = 0;
            int res = 1 / 0;
        } catch (Exception e) {
            logger.error("我的错,e : {}",e);
        }
    }
}
