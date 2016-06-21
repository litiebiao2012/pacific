package com.pacific.web.controller;

import com.pacific.common.web.result.AjaxResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Fe on 16/6/21.
 */
@Controller
@RequestMapping("/application")
public class ApplicationController {

    @RequestMapping("/saveApplication.json")
    public AjaxResult saveApplication() {
        return null;
    }
}
