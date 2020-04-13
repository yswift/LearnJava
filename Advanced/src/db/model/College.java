package db.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
//@Table(name="\"College\"")
//@Table(name="College")
public class College {
	@Id
	private String id;
	private String name;

	public College() {
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

	public College(String id, String name) {
		this.id = id;
		this.name = name;
	}

	@Override
	public String toString() {
		return "College [id=" + id + ", name=" + name + "]";
	}
}
