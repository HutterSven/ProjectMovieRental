package ch.hevs.movierentalservice;

import java.util.List;

import javax.ejb.Local;

import ch.hevs.businessobject.Employee;
import ch.hevs.businessobject.Movie;
import ch.hevs.businessobject.Renter;
import ch.hevs.businessobject.Store;

@Local
public interface MovieRentalStore {

	Renter getRenter(String lastNameRenter, String firstNameRenter);
	
	public List<Movie> getRentersMovies(String lastNameRenter, String firstNameRenter);

	void rentMovie(Store store, Renter renter, Movie movie) throws Exception;

	void returnMovie(Store store, Renter renter, Movie movie) throws Exception;

	List<Renter> getRenters();
	
	List<Employee> getEmployees();
	
	Store getStore(long storeID);
}
