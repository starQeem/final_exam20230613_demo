package com.starqeem.final_exam20230613_demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Date: 2023/6/15 16:28
 * @author: Qeem
 */
@Controller
@RequestMapping("/film")
public class filmController {
    /*
     * 跳转到普通用户电影详情
     * */
    @GetMapping("/common/1")
    public String common1(){
        return "detail/common1";
    }
    @GetMapping("/common/2")
    public String common2(){
        return "detail/common2";
    }
    /*
     * 跳转到vip用户电影详情
     * */
    @GetMapping("/vip/1")
    public String vip1(){
        return "detail/vip1";
    }
    @GetMapping("/vip/2")
    public String vip2(){
        return "detail/vip2";
    }
}
