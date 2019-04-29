package demo.controller;

import demo.model.College;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/demo")
public class DemoViewNameController {
    @GetMapping("param")
    public void param(Model model) {
        String msg = "视图名称，隐式转换";
        model.addAttribute("msg", msg);
    }

    @GetMapping("college")
    @ModelAttribute("c")
    public College getCollege() {
        return new College("01", "计算机科学与技术");
    }
}
