package db.springdata;

import org.springframework.data.jpa.repository.JpaRepository;

import db.model.College;

public interface CollegeRepository extends JpaRepository<College, String> {
	College findByName(String name);
}
