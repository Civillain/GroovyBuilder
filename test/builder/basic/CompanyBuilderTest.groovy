package builder.basic

import org.junit.Test

import builder.basic.CompanyBuilder
import builder.basic.model.Company
import builder.basic.model.Department
import builder.basic.model.Employee

class CompanyBuilderTest {
	
	@Test
	public void test() {
		
		CompanyBuilder builder = new CompanyBuilder()
		
		Company company = builder.company('ABC') {
			department('XYZ') {
				employee('emp12345') {
					name('John')
					role('Administrator')
				}
			}
			department('123') {
				
				employee('emp987') {
					name('Karen')
					role('Project Manager')
				}
				
				department('456') {
					employee('emp456') {
						name('Mary')
						role('Developer')
					}
					
				}
			}
		}
		
		assert company != null && company.id == 'ABC'
		assert company.departments.size() == 2
		
		Department dept1 = (Department)company.departments[0] 
		Department dept2 = (Department)company.departments[1]
		
		
		assert dept1.id == 'XYZ' 
		assert dept2.id == '123'

		assert dept1.departments.size() == 0
		assert dept2.departments.size() == 1
		
		Department dept3 = (Department)dept2.departments[0]
		
		assert dept1.employees.size() == 1
		assert dept2.employees.size() == 1
		assert dept3.employees.size() == 1
		
		Employee emp1 = (Employee) dept1.employees[0]
		Employee emp2 = (Employee) dept2.employees[0]
		Employee emp3 = (Employee) dept3.employees[0]
		
		assert emp1.name == 'John' && emp1.role == 'Administrator'
		assert emp2.name == 'Karen' && emp2.role == 'Project Manager'
		assert emp3.name == 'Mary' && emp3.role == 'Developer'
		
	}
	
}
