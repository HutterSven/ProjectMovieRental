package ch.hevs.businessobject;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Renter")
public class Renter extends Person {	

	@OneToMany
	private List<Movie> movies;
	
	// constructors
	public Renter() {
		movies = new ArrayList<Movie>();
	}
	public Renter(List<Movie> movies) {
		this.movies = movies;
	}
}