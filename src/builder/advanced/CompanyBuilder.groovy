package builder.advanced

import builder.advanced.model.Company
import builder.advanced.model.Department
import builder.advanced.model.Employee
import builder.advanced.model.Payment
import builder.advanced.service.PaymentService;

/**
 * An example of a basic Groovy Builder.
 * 
 */
class CompanyBuilder extends BuilderSupport {

	private Queue<Employee> paymentQueue = new LinkedList<>()
	
	private PaymentService paymentService = new PaymentService()
	
	@Override
	protected Object createNode(Object name, Object id) {
		switch(name) {
			case 'company': return createCompany(id)
			case 'department': return createDepartment(id)
			case 'employee': return createEmployee(id)
			case 'name': return setEmployeeName(id)
			case 'role': return setEmployeeRole(id)
			default: throw new IllegalArgumentException("Invalid keyword $name")
		}
	}
	
	@Override
	protected Object createNode(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	protected Object createNode(Object arg0, Map arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Object createNode(Object arg0, Map arg1, Object arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setParent(Object parent, Object child) {
		
	}
	
	Employee setEmployeeName(String name) {
		if(current instanceof Employee) {
			Employee employee = (Employee) current
			employee.setName(name)
		} else {
			throw new IllegalArgumentException("Invalid keyword 'name'")
		}
	}
	
	Employee setEmployeeRole(String role) {
		if(current instanceof Employee) {
			Employee employee = (Employee) current
			employee.setRole(role)
		} else {
			throw new IllegalArgumentException("Invalid keyword 'role'")
		}
	}

	Company createCompany(String id) {
		Company company = new Company(id)
		return company
	}
	
	Department createDepartment(String id) {
		Department department = new Department(id)
		if(current instanceof Company) {
			Company company = (Company) current
			company.getDepartments().add(department)
		} else if(current instanceof Department) {
			Department parentDep = (Department) current
			parentDep.getDepartments().add(department)
		}
		return department
	}
	
	Employee createEmployee(String id) {
		Employee employee = new Employee(id)
		if(current instanceof Department) {
			Department department = (Department) current
			department.getEmployees().add(employee)
		}
		
		employee.metaClass.pay = payEmployee
		return employee
	}
	
	def payEmployee = { String  amount, Closure c = {} ->
		Employee employee = (Employee) delegate
		paymentQueue.add(employee)
		Payment payment = new Payment()
		payment.setAmount(amount)
		employee.metaClass.payment = payment
		setCurrent(employee)
		c.call()
	}
	
	void perform() {
		paymentQueue.each { Employee emp ->
			paymentService.pay(emp, emp.payment)
		}
	}
}
