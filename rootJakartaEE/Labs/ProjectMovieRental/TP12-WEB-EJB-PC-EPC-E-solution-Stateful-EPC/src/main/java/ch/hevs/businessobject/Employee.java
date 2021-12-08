package ch.hevs.businessobject;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Employee")
public class Employee extends Person {	

	
	// constructors
	public Employee() {
	}
}