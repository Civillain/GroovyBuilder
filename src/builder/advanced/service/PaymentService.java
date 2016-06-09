package builder.advanced.service;

import builder.advanced.model.Employee;
import builder.advanced.model.Payment;

public class PaymentService {

	public void pay(Employee employee, Payment payment) {
		System.out.println(employee.getName() + " was paid: " + payment.getAmount());
	}
	
}
