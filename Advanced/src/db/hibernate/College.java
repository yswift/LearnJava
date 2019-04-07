package db.hibernate;

class College {
	private String Id;
	private String Name;

	public College() {
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public College(String id, String name) {
		super();
		Id = id;
		Name = name;
	}

	@Override
	public String toString() {
		return "College [Id=" + Id + ", Name=" + Name + "]";
	}
}
