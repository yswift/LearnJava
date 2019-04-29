package demo.controller;

import demo.model.College;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/returnValue")
public class DemoReturnValueController {
    @RequestMapping("json")
    @ResponseBody
    public College json() {
        return new College("01", "计算机科学与技术");
    }

    @RequestMapping("college")
    public ResponseEntity<College> getImageAsResponseEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        College c = new College("01", "计算机科学与技术");
        ResponseEntity<College> responseEntity = new ResponseEntity<>(c, headers, HttpStatus.OK);
        return responseEntity;
    }

    // 重定向到json
    @RequestMapping("redirect")
    public String redirect() {
        return "redirect:json";
    }

    // 重定向到json
    @RequestMapping("redirect2")
    public View redirect2() {
        return new RedirectView("json", true);
    }

}
