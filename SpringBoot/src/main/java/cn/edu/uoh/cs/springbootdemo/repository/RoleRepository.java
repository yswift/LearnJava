package cn.edu.uoh.cs.springbootdemo.repository;

import cn.edu.uoh.cs.springbootdemo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, String> {
    List<Role> findByIdIn(String[] roleIds);
}
