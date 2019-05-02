package cn.edu.uoh.cs.springbootdemo.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Users")
public class User {
	@Id
	private String id;

	private String name;

	private String password;

	@Column(nullable = false)
	private String departmentId;

	@ManyToOne()
	@JoinColumn(name = "departmentId", referencedColumnName = "id", insertable = false, updatable = false)
	private Department department;

	@ManyToMany()
	@JoinTable(name = "User_Role", joinColumns = @JoinColumn(name = "UserId"), inverseJoinColumns = @JoinColumn(name = "RoleId"))
	private List<Role> roles = new ArrayList<>();

	public User() {
	}

	public User(String id, String name, String departmentId) {
		this.id = id;
		this.name = name;
		this.departmentId = departmentId;
	}

	public User(String id, String name, Department dep) {
		this.id = id;
		this.name = name;
		this.department = dep;
		this.departmentId = dep.getId();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", departmentId=" + departmentId + ", department=" + department
				+ ", roles=" + roles + "]";
	}

}
