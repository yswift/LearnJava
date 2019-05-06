package cn.edu.uoh.cs.springbootdemo.controller;

import cn.edu.uoh.cs.springbootdemo.model.User;
import cn.edu.uoh.cs.springbootdemo.service.UserService;
import cn.edu.uoh.cs.springbootdemo.utils.SelectListItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController extends EntityController<User, String> {
    @Autowired
    UserService userService;

    @Override
    protected User newInstance() {
        return new User();
    }

    @Override
    protected String getEditView() {
        return "/user/edit";
    }

    @Override
    public String save(User entity, HttpServletRequest request, RedirectAttributes model) {
        String[] roles = request.getParameterValues("role");
        userService.save(entity, roles);
        String msg = "保存【"+entity+"】成功";
        model.addFlashAttribute("msg", msg);
        return "redirect:list";
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
