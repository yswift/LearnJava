package cn.edu.uoh.cs.springbootdemo.repository;

import cn.edu.uoh.cs.springbootdemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
