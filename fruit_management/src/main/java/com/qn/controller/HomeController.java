package com.qn.controller;

import com.qn.utils.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("")
public class HomeController {
    @Autowired
    private AppUtil appUtil;

    @GetMapping("/login")

    public ModelAndView showLoginPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/login");
        String username = appUtil.getPrincipalUsername();

        modelAndView.addObject("username", username);
        return modelAndView;
    }

    @GetMapping("/register")
    public ModelAndView showRegisterPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/register");
        return modelAndView;
    }

    @GetMapping("/index")
    public ModelAndView showPageProduct() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/index");
        return modelAndView;
    }

    @GetMapping("/products")
    public ModelAndView showProductPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/product/list");
        return modelAndView;
    }

    @GetMapping("/users")
    public ModelAndView showListUser() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/user/list");
        return modelAndView;
    }
}
