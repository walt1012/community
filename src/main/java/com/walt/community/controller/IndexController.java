package com.walt.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @FileName: community
 * @Author: walt1012
 * @Description: TODO
 * @Date: 2020/2/16 9:48 下午
 */

@Controller
public class IndexController {

    @GetMapping("/")
    public String hello() {
        return "index";
    }
}
