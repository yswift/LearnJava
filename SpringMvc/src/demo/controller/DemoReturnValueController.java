package demo.controller;

import demo.model.College;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

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

}
