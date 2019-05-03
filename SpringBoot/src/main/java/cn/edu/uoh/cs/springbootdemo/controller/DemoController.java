package cn.edu.uoh.cs.springbootdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DemoController {
    @RequestMapping("/demo")
    public String home(Model model) {
        model.addAttribute("msg", "test");
        return "demo";
    }

    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        return "home hot dploye true?";
    }
}
