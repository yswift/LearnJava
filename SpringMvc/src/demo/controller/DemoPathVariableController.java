package demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pathVar")
public class DemoPathVariableController {
    @RequestMapping("p1/{id}")
    public String p1(@PathVariable(name="id") String name, Model model) {
        String msg = String.format("客户端请求 name = %s", name);
        model.addAttribute("msg", msg);
        return "demo/param";
    }

    @RequestMapping("p2/{year}/{month}")
    public String p2(@PathVariable(name="year") String year, @PathVariable(name="month") String month, Model model) {
        String msg = String.format("客户端请求 %s年%s月", year, month);
        model.addAttribute("msg", msg);
        return "demo/param";
    }

    @RequestMapping("p3/{year:\\d{4}}/{month:\\d{1,2}}")
    public String p3(@PathVariable(name="year") String year, @PathVariable(name="month") String month, Model model) {
        String msg = String.format("客户端请求 %s年%s月", year, month);
        model.addAttribute("msg", msg);
        return "demo/param";
    }
}
