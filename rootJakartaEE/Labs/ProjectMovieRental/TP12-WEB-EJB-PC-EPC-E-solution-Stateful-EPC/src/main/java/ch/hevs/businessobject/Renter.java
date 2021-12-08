package ch.hevs.businessobject;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Renter")
public class Renter extends Person {	

	
	// constructors
	public Renter() {
	}
}
