package com.example.demo.controller;

import com.example.demo.bean.Login;
import com.example.demo.repository.LoginRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(path = "/user")
public class LoginController {
    @Autowired
    private LoginRepository loginRepo;

    @GetMapping("/login")
    public ModelAndView login(ModelAndView model){
        model.setViewName("common/login.html");
        return model;
    }

    @PostMapping("/login")
    public String login(@ModelAttribute Login login, RedirectAttributes ra) {
        String username = login.getUsername();
        String password = login.getPassword();

        System.out.println(loginRepo.findOneByUsername(username).toString());

        ra.addFlashAttribute("error", "yes");
        return "redirect:/user/login";
    }
}
