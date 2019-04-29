package demo.controller;

import demo.model.College;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/request")
public class DemoRequestController {
    // 接收参数 name，参数可选
    @RequestMapping("p1")
    public String p1(String name, Model model) {
        String msg = String.format("客户端请求 name = %s", name);
        model.addAttribute("msg", msg);
        return "demo/param";
    }

    // 必须提供name参数
    @RequestMapping("p2")
    public String p2(@RequestParam String name, Model model) {
        String msg = String.format("客户端请求 name = %s", name);
        model.addAttribute("msg", msg);
        return "demo/param";
    }

    // 客户端提供的参数和形参的名称不一样
    @RequestMapping("p3")
    public String p3(@RequestParam("req") String name, Model model) {
        String msg = String.format("客户端请求 name = %s", name);
        model.addAttribute("msg", msg);
        return "demo/param";
    }

    // 设定默认值
    @RequestMapping("p4")
    public String p4(@RequestParam(name="req", defaultValue = "请求默认值") String name, Model model) {
        String msg = String.format("客户端请求 req = %s", name);
        model.addAttribute("msg", msg);
        return "demo/param";
    }

    // 整体参数
    @RequestMapping("p5")
    public String p5(College college, Model model) {
        String msg = String.format("客户端请求 %s", college.toString());
        model.addAttribute("msg", msg);
        return "demo/param";
    }
}
