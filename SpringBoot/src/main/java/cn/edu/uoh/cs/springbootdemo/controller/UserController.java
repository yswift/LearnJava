package cn.edu.uoh.cs.springbootdemo.controller;

import cn.edu.uoh.cs.springbootdemo.model.User;
import cn.edu.uoh.cs.springbootdemo.service.UserService;
import cn.edu.uoh.cs.springbootdemo.utils.SelectListItem;
import cn.edu.uoh.cs.springbootdemo.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController extends EntityController<User, String> {
    @Autowired
    UserService userService;

    @Override
    public String save(User entity, HttpServletRequest request) {
        String[] roles = request.getParameterValues("role");
        userService.save(entity, roles);
        String msg = StringUtil.encodeValue("保存【"+entity+"】成功");
        return "redirect:list?msg="+msg;
    }

    @Override
    protected User newInstance() {
        return new User();
    }

    @Override
    protected String getEditView() {
        return "/user/edit";
    }

    @Override
    protected void initForeignKeyList(Model model, User entity) {
        super.initForeignKeyList(model, entity);

        List<SelectListItem> departments = userService.getDepartments(entity);
        model.addAttribute("departments", departments);

        List<SelectListItem> roles = userService.getRoles(entity);
        model.addAttribute("roles", roles);
    }
}
