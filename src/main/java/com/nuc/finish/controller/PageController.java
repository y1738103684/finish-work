package com.nuc.finish.controller;

import com.nuc.finish.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Array;

/**
 * @author 尉一飞
 * @Description
 * @Date 创建于 2020/4/3 9:16
 */
@Controller
public class PageController extends BaseController{
    public static final String PREFIX = "views/";
    public static final String INDEX_PAGE = "views/index";
    public static final String LOGIN_PAGE = "views/login";

    @RequestMapping("/")
    public String defaultIndex() {
        return INDEX_PAGE;
    }

    @RequestMapping("/index")
    public String index() {
        return INDEX_PAGE;
    }

    @RequestMapping("/login")
    public String login() {
        return LOGIN_PAGE;
    }

    @RequestMapping("/{path}")
    public String page(@PathVariable String path) {
        return PREFIX + path;
    }
}
