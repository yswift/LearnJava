package cn.edu.uoh.cs.springbootdemo.repository;

import cn.edu.uoh.cs.springbootdemo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, String> {
    /**
     * 查找指定角色Id的角色，
     * @param roleIds 角色id数组
     * @return 角色list
     */
    List<Role> findByIdIn(String[] roleIds);
}
