package cn.edu.uoh.cs.springbootdemo.controller;

import cn.edu.uoh.cs.springbootdemo.model.User;
import cn.edu.uoh.cs.springbootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AccountController {
    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String login() {
        return "account/login";
    }

    @GetMapping("/account/access-denied")
    public String accessDenied() {
        return "account/access-denied";
    }

    @GetMapping("/account/changePassword")
    public String changPassword() {
        return "account/changPassword";
    }

    @PostMapping("/account/changPassword")
    public String changPassword(String userId, String oldPwd, String newPwd) {
        User user = userService.getById(userId);
        boolean r = userService.changePassword(user, oldPwd, newPwd);
        return "redirect:/";
    }
}
