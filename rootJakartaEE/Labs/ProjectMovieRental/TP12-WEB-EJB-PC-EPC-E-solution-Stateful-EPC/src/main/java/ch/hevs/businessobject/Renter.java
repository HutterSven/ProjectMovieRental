package ch.hevs.businessobject;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Renter")
public class Renter extends Person {	

	@OneToMany(cascade = CascadeType.REMOVE)
	private List<Movie> movies;
	
	// constructors
	public Renter() {
		movies = new ArrayList<Movie>();
	}
	public Renter(List<Movie> movies, String firstname, String lastname, Address address) {
		this.movies = movies;
		this.setLastname(lastname);
		this.setFirstname(firstname);
		this.setAddress(address);
	}
	
	//methods
	public void rentMovie(Movie movie) {
		movies.add(movie);
	}
	
	public void returnMovie(Movie movie) {
		movies.remove(movie);
	}
}
