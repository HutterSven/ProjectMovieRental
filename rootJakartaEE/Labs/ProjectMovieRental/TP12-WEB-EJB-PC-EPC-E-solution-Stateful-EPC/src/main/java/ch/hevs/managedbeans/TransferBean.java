package ch.hevs.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ValueChangeEvent;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.hevs.businessobject.Movie;
import ch.hevs.businessobject.Renter;
import ch.hevs.businessobject.Store;
import ch.hevs.movierentalservice.MovieRentalStore;

/**
 * TransferBean.java
 * 
 */

public class TransferBean
{
    private List<Renter> renters;
    private List<String> renterNames;
    private List<Store> stores;
    private List<String> storeIDs;
    private List<Movie> movies;
    private List<String> movieNames;
    private List<Movie> renterMovies;
    private List<String> renterMovieNames;
    private String rentalResult;
    private MovieRentalStore movieRentalStore;
    public String renterName;
    private String renterFirstName;
    private String renterLastName;
    private long storeID;
    private Movie movie;
    private String movieName;
    
    @PostConstruct
    public void initialize() throws NamingException {
    	
    	// use JNDI to inject reference to bank EJB
    	InitialContext ctx = new InitialContext();
		movieRentalStore = (MovieRentalStore) ctx.lookup("java:global/TP12-WEB-EJB-PC-EPC-E-0.0.1-SNAPSHOT/MovieRentalStoreBean!ch.hevs.movierentalservice.MovieRentalStore");
			
    	// get renters
		renters = movieRentalStore.getRenters();
		this.renterNames = new ArrayList<String>();
		for (Renter renter : renters) {
			this.renterNames.add(renter.getFirstname() + " " + renter.getLastname());
		}
		
		// get stores
		stores = movieRentalStore.getStores();
		this.storeIDs = new ArrayList<String>();
		for (Store store : stores) {
			this.storeIDs.add(String.valueOf(store.getId()));
		}
				
		// get movies
		movies = movieRentalStore.getMovies();
		this.movieNames = new ArrayList<String>();
		for (Movie movie : movies) {
			this.movieNames.add(movie.getTitle());
		}

			
		//get renterName
		this.renterName = "Hans";
		
		this.storeID = 0;
		
		this.movieName = "Lotr";		
		
    }
    
    public List<Renter> getRenters() {
		return renters;
	}

	public void setRenters(List<Renter> renters) {
		this.renters = renters;
	}

	public List<String> getRenterNames() {
		return renterNames;
	}

	public void setRenterNames(List<String> renterNames) {
		this.renterNames = renterNames;
	}

	public List<Store> getStores() {
		return stores;
	}

	public void setStores(List<Store> stores) {
		this.stores = stores;
	}

	public List<String> getStoreIDs() {
		return storeIDs;
	}

	public void setStoreIDs(List<String> storeNames) {
		this.storeIDs = storeNames;
	}

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

	public List<String> getMovieNames() {
		return movieNames;
	}

	public void setMovieNames(List<String> movieNames) {
		this.movieNames = movieNames;
	}

	public List<Movie> getRenterMovies() {
		return renterMovies;
	}

	public void setRenterMovies(List<Movie> renterMovies) {
		this.renterMovies = renterMovies;
	}

	public List<String> getRenterMovieNames() {
		return renterMovieNames;
	}

	public void setRenterMovieNames(List<String> renterMovieNames) {
		this.renterMovieNames = renterMovieNames;
	}

	public MovieRentalStore getMovieRentalStore() {
		return movieRentalStore;
	}

	public void setMovieRentalStore(MovieRentalStore movieRentalStore) {
		this.movieRentalStore = movieRentalStore;
	}

	public String getRenterName() {
		return renterName;
	}

	public void setRenterName(String renterName) {
		this.renterName = renterName;
	}

	public String getRenterFirstName() {
		return renterFirstName;
	}

	public void setRenterFirstName(String renterFirstName) {
		this.renterFirstName = renterFirstName;
	}

	public String getRenterLastName() {
		return renterLastName;
	}

	public void setRenterLastName(String renterLastName) {
		this.renterLastName = renterLastName;
	}

	public long getStoreID() {
		return storeID;
	}

	public void setStoreID(long storeID) {
		this.storeID = storeID;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	// movieRented
    public Movie getMovieRented () {
    	return movie;
    }
    public void setMovieRented (final Movie movie) {
    	this.movie = movie;
    }
    
    // rentalResult
    public String getRentalResult () {
    	return rentalResult;
    }
	public void setRentalResult(String rentalResult) {
		this.rentalResult = rentalResult;
	}
    

	public void updateRenter(ValueChangeEvent event) {
		
    	this.renterName = (String)event.getNewValue().toString();
		
    	this.renterFirstName = this.renterName.split(" ")[0];
    	this.renterLastName = this.renterName.split(" ")[1];
    	

//    	
//	    List<Account> accounts = bank.getAccountListFromClientLastname(this.sourceClientName);
//	    this.sourceAccountDescriptions = new ArrayList<String>();
//		for (Account account : accounts) {
//			this.sourceAccountDescriptions.add(account.getDescription());
//		}
    }
	public void updateStore(ValueChangeEvent event) {
    	this.storeID = (long)event.getNewValue();
//			
//	    List<Account> accounts = bank.getAccountListFromClientLastname(this.destinationClientName);
//	    this.destinationAccountDescriptions = new ArrayList<String>();
//		for (Account account : accounts) {
//			this.destinationAccountDescriptions.add(account.getDescription());
//		}
    }
    
    public String performRental() {
    	
    	try {
			Renter renter = movieRentalStore.getRenter(renterFirstName, renterLastName);
			Store store = movieRentalStore.getStore(storeID);
			
			movieRentalStore.rentMovie(store, renter, movie);
			this.rentalResult="Success!";
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "showRentalResult"; //  the String value returned represents the outcome used by the navigation handler to determine what page to display next.
	} 
    
    public String performReturnal() {
    	
    	try {
			Renter renter = movieRentalStore.getRenter(renterFirstName, renterLastName);
			Store store = movieRentalStore.getStore(storeID);
			
			movieRentalStore.returnMovie(store, renter, movie);
			this.rentalResult="Success!";
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "showRentalResult"; //  the String value returned represents the outcome used by the navigation handler to determine what page to display next.
	} 
}