package db.springdata;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import db.model.College;

public interface CollegeRepository extends JpaRepository<College, String> {
	College findByName(String name);
}
