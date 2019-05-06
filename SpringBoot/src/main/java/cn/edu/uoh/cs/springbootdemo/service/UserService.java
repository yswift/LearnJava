package cn.edu.uoh.cs.springbootdemo.service;

import cn.edu.uoh.cs.springbootdemo.model.Role;
import cn.edu.uoh.cs.springbootdemo.model.User;
import cn.edu.uoh.cs.springbootdemo.repository.DepartmentRepository;
import cn.edu.uoh.cs.springbootdemo.repository.RoleRepository;
import cn.edu.uoh.cs.springbootdemo.repository.UserRepository;
import cn.edu.uoh.cs.springbootdemo.utils.SelectListItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    RoleRepository roleRepository;

    public User getById(String id) {
        Optional<User> ou = userRepository.findById(id);
        return ou.orElse(null);
    }

    public void save(User user, String[] roles) {
        // 保存时不修改密码
        Optional<User> ou = userRepository.findById(user.getId());
        if (ou.isPresent()) {
            user.setPassword(ou.get().getPassword());
        } else {
            // 如果是新用户，初始密码是123456
            user.setPassword("123456");
        }
        if (roles == null) {
            roles = new String[]{};
        }
        List<Role> roleList = roleRepository.findByIdIn(roles);
        user.setRoles(roleList);
        userRepository.save(user);
    }

    // TODO 密码不保存明文，编辑时不修改用户密码
    public boolean changePassword(User user, String oldPwd, String newPwd) {
        if (oldPwd.equals(user.getPassword())) {
            user.setPassword(newPwd);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    // 获取部门列表，并把指定用户所属部门的selected设为true
    public List<SelectListItem> getDepartments(User user) {
        return departmentRepository.findAll()
                .stream()
                .map((d) -> new SelectListItem(d.getId(), d.getName(),
                        user != null && d.getId().equals(user.getDepartmentId())))
                .collect(Collectors.toList());
    }

    // 获取角色列表，并把用户拥有的角色的selected设为true
    public List<SelectListItem> getRoles(User user) {
        return roleRepository.findAll()
                .stream()
                .map((d) -> new SelectListItem(d.getId(), d.getName(), user!=null && user.isInRole(d.getId())))
                .collect(Collectors.toList());
    }
}
