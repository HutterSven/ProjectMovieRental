package ch.hevs.movierentalservice;

import java.util.List;

import javax.ejb.Local;

import ch.hevs.businessobject.Movie;
import ch.hevs.businessobject.Renter;
import ch.hevs.businessobject.Store;

@Local
public interface MovieRentalStore {

	Renter getRenter(Renter renter);
	
	public List<Movie> getRentersMovies(String lastname);

	void transfer(Store store, Renter renter, Movie movie) throws Exception;

	List<Renter> getRenters();

	Renter getClient(long renterId);
}
