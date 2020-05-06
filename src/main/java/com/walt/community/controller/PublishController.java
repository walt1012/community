package com.walt.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @FileName: community
 * @Author: walt1012
 * @Description: TODO
 * @Date: 2020/2/22 10:22 下午
 */

@Controller
public class PublishController {

    @GetMapping("publish")
    public String publish() {
        return "publish";
    }
}
