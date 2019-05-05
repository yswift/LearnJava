package cn.edu.uoh.cs.springbootdemo.controller;

import cn.edu.uoh.cs.springbootdemo.model.Department;
import cn.edu.uoh.cs.springbootdemo.repository.DepartmentRepository;
import cn.edu.uoh.cs.springbootdemo.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/department")
public class DeparmentController {
    @Autowired
    DepartmentRepository repository;

    @GetMapping("list") // 映射到 /department/list，默认的view文件名也是：/department/list
    @ModelAttribute("page") // 返回值的属性是 page
    public Page<Department> list(Model model, String msg,
                                 @RequestParam(name="pageNo", defaultValue = "0") int pageNo,
                                 @RequestParam(name="pageSize", defaultValue = "10") int pageSize) {
        // 分页消息，默认第0页，每页10条数据
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Department> page = repository.findAll(pageable);
        // 增删改 会传递一条消息，用于显示
        if (!StringUtil.isEmpty(msg)) {
            model.addAttribute("msg", msg);
        }
        return page;
    }

    @GetMapping("create")
    public void create() {
    }

    // create，edit都用此方法保存
    @PostMapping("save")
    public String save(Department entity) {
        repository.save(entity);
        String msg = StringUtil.encodeValue("保存【"+entity+"】成功");
        return "redirect:list?msg="+msg;
    }

    @GetMapping("edit/{id}")
    public String edit(Model model, @PathVariable String id) {
        Department entity = repository.findById(id).get();
        model.addAttribute("entity", entity);
        return "/department/edit";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable String id) {
        repository.deleteById(id);
        String msg = StringUtil.encodeValue("删除【"+id+"】成功");
        return "redirect:/department/list?msg="+msg;
    }
}
