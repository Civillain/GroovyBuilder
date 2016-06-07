package builder.basic.model;

import java.util.ArrayList;
import java.util.List;

public class Company {
	private List<Department> departments;
	private final String id;
	
	public Company(String id) {
		this.id = id;
		this.departments = new ArrayList<>();
	}

	public List<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}
	
	public String getId() {
		return id;
	}
}
