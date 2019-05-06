package cn.edu.uoh.cs.springbootdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

public abstract class EntityController<T, IdType> {
    @Autowired
    protected JpaRepository<T, IdType> repository;

    // 创建实体对象
    protected abstract T newInstance();

    // 返回create，edit的视图名称
    protected abstract String getEditView();

    @GetMapping("list")
    @ModelAttribute("page")
    public Page<T> list(Model model, String msg,
                        @RequestParam(name = "pageNo", defaultValue = "0") int pageNo,
                        @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<T> page = repository.findAll(pageable);
        return page;
    }

    @GetMapping("create")
    public String create(Model model) {
        model.addAttribute("action", "create");
        model.addAttribute("entity", newInstance());
        initForeignKeyList(model, null);
        return getEditView();
    }

    // create，edit都用此方法保存
    @PostMapping("save")
    public String save(T entity, HttpServletRequest request, RedirectAttributes model) {
        repository.save(entity);
        String msg = "保存【" + entity + "】成功";
        model.addFlashAttribute("msg", msg);
        return "redirect:list";
    }

    @GetMapping("edit")
    public void edit(Model model, @RequestParam IdType id) {
        T entity = repository.findById(id).get();
        model.addAttribute("action", "edit");
        model.addAttribute("entity", entity);
        initForeignKeyList(model, entity);
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable IdType id, RedirectAttributes model) {
        repository.deleteById(id);
        String msg = "删除【" + id + "】成功";
        model.addFlashAttribute("msg", msg);
        return "redirect:../list";
    }

    // 生成必要外键的列表，用于dropdown或checkbox选择
    protected void initForeignKeyList(Model model, T entity) {
    }
}
