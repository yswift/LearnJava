package demo.controller;

import demo.model.Student;
import demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentRepository repository;

    @GetMapping("list")
    @ModelAttribute("list")
    public List<Student> list() {
        return repository.findAll();
    }

    // get 方法，显示用户输入UI
    @GetMapping("create")
    public void create() {
    }

    // 用户提交信息，用此方法保存
    @PostMapping("create")
    public String create(Student s, MultipartFile photofile) {
        uploadPhoto(s, photofile);
        repository.save(s);
        String msg = "保存【" + s.getName() + "】成功！";
        return "redirect:/student/list?msg=" + encodeValue(msg);
    }

    private void uploadPhoto(Student s, MultipartFile photofile) {
        try {
            if (photofile != null && photofile.getSize() > 0) {
                s.setPhoto(photofile.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("edit/{id:\\d+}")
    public String edit(@PathVariable int id, Model model) {
        Student student =  repository.findById(id).get();
        model.addAttribute(student);
        return "student/edit";
    }

    @PostMapping("edit/{id:\\d+}")
    public String edit(@PathVariable int id, Student s, MultipartFile photofile) {
        Student student =  repository.findById(id).get();
        // 只有上传照片了，才更新
        if (photofile != null && photofile.getSize() > 0) {
            uploadPhoto(s, photofile);
        } else {
            s.setPhoto(student.getPhoto());
        }
        repository.save(s);
        String msg = "保存【" + s.getName() + "】成功！";
        return "redirect:/student/list?msg=" + encodeValue(msg);
    }

    @GetMapping("detail/{id:\\d+}")
    public String detail(@PathVariable int id, Model model) {
        Student student =  repository.findById(id).get();
        model.addAttribute(student);
        return "student/detail";
    }

    @GetMapping("photo/{id:\\d+}")
    public ResponseEntity<byte[]> photo(@PathVariable int id) {
        Student s = repository.findById(id).get();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(s.getPhoto(), headers, HttpStatus.OK);
    }

    @GetMapping("delete/{id:\\d+}")
    public String delete(@PathVariable int id) {
        repository.deleteById(id);
        String msg = "删除成功！";
        return "redirect:/student/list?msg=" + encodeValue(msg);
    }

    private String encodeValue(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return value;
        }
    }
}
