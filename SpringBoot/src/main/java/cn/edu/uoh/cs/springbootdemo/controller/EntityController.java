package cn.edu.uoh.cs.springbootdemo.controller;

import cn.edu.uoh.cs.springbootdemo.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

public abstract class EntityController<T, IdType> {
    @Autowired
    protected JpaRepository<T, IdType> repository;

    protected abstract T newInstance();

    protected abstract String getEditView();

    @GetMapping("list")
    @ModelAttribute("page")
    public Page<T> list(Model model, String msg,
                                 @RequestParam(name="pageNo", defaultValue = "0") int pageNo,
                                 @RequestParam(name="pageSize", defaultValue = "10") int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<T> page = repository.findAll(pageable);
        if (!StringUtil.isEmpty(msg)) {
            model.addAttribute("msg", msg);
        }
//        model.addAttribute("page", page);
        return page;
    }

    @GetMapping("create")
    public String create(Model model) {
        model.addAttribute("action", "create");
        model.addAttribute("entity", newInstance());
        return getEditView();
    }

    // create，edit都用此方法保存
    @PostMapping("save")
    public String save(T entity) {
        repository.save(entity);
        String msg = StringUtil.encodeValue("保存【"+entity+"】成功");
        return "redirect:list?msg="+msg;
    }

    @GetMapping("edit")
    public String edit(Model model, @RequestParam IdType id) {
        T entity = repository.findById(id).get();
        model.addAttribute("action", "edit");
        model.addAttribute("entity", entity);
        return getEditView();
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable IdType id) {
        repository.deleteById(id);
        String msg = StringUtil.encodeValue("删除【"+id+"】成功");
        return "redirect:../list?msg="+msg;
    }
}
