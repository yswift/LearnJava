package cn.edu.uoh.cs.springbootdemo.controller;

import cn.edu.uoh.cs.springbootdemo.model.Role;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/role")
public class RoleController extends EntityController<Role, String> {
    @Override
    protected Role newInstance() {
        return new Role();
    }

    @Override
    protected String getEditView() {
        return "/role/edit";
    }
}
