package com.el.cloud.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Roman.Zhang
 * @date 2020/3/31
 * @description:
 */
@RestController
public class HelloController {
	
    @RequestMapping("/hello")
    public String index() {
        return "Hello World";
    }
}