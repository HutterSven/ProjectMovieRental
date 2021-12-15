package ch.hevs.businessobject;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Employee")
public class Employee extends Person {	

	
	// constructors
	public Employee() {
	}
	
	public Employee(String firstname, String lastname, Address address) {
		this.setLastname(lastname);
		this.setFirstname(firstname);
		this.setAddress(address);
	}
}