package builder.basic.model;

public class Employee {
	private String name;
	private String role;
	private final String id;
	
	public Employee(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	public String getId() {
		return id;
	}
}
