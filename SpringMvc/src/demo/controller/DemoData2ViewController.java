package demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/data2view")
public class DemoData2ViewController {
    @RequestMapping("model")
    public String f1(Model model) {
        String msg = "使用model传递的数据";
        model.addAttribute("msg", msg);
        return "demo/param";
    }

    @RequestMapping("modelMap")
    public String f2(ModelMap modelMap) {
        String msg = "使用modelMap传递的数据";
        modelMap.addAttribute("msg", msg);
        return "demo/param";
    }

    @RequestMapping("modelAndView")
    public ModelAndView f3() {
        String msg = "使用ModelAndView传递的数据";
        ModelAndView modelAndView = new ModelAndView("demo/param");
        modelAndView.addObject("msg", msg);
        return modelAndView;
    }
}
