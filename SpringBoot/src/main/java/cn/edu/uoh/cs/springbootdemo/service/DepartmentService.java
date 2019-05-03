package cn.edu.uoh.cs.springbootdemo.service;

import cn.edu.uoh.cs.springbootdemo.model.Department;
import cn.edu.uoh.cs.springbootdemo.repository.DepartmentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

//@Component
public class DepartmentService {
//    @Autowired
    private DepartmentRepository repository;

    public Department save(Department entity) {
        return repository.save(entity);
    }

    public Page<Department> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Optional<Department> findById(String id) {
        return repository.findById(id);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
