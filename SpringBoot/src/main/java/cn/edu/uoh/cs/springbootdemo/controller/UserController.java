package cn.edu.uoh.cs.springbootdemo.controller;

import cn.edu.uoh.cs.springbootdemo.model.Role;
import cn.edu.uoh.cs.springbootdemo.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController extends EntityController<User, String> {
    @Override
    protected User newInstance() {
        return new User();
    }

    @Override
    protected String getEditView() {
        return "/user/edit";
    }
}
