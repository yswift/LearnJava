package cn.edu.uoh.cs.springbootdemo.repository;

import cn.edu.uoh.cs.springbootdemo.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, String> {
}
