package ch.hevs.businessobject;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Store")
public class Store {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	
	@OneToMany
	private List<Employee> employees;
	
	@OneToMany
	private List<Movie> movies;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}
	
	public Store() {
		employees = new ArrayList<Employee>();
		movies = new ArrayList<Movie>();
	}
	
	public Store(Address address, List<Movie> movies, List<Employee> employees) {
		this.address = address;
		this.movies = movies;
		this.employees = employees;
	}

	
	@Embedded
	private Address address;
	

}
