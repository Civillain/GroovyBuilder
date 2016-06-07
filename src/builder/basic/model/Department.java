package builder.basic.model;

import java.util.ArrayList;
import java.util.List;

public class Department {
	private List<Employee> employees;
	private List<Department> departments;
	private final String id;
	
	public Department(String id) {
		this.id = id;
		this.employees = new ArrayList<>();
		this.departments = new ArrayList<>();
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
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
