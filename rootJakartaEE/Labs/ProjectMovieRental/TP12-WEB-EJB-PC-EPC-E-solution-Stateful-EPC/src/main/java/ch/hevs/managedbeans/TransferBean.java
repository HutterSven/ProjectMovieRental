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
	private String renterMovieName;
	private String rentalResult;
	private MovieRentalStore movieRentalStore;
	public String renterName;
	private String renterFirstName;
	private String renterLastName;
	private long storeID;
	private Movie movie;
	private String movieName;
	private String removeResult;

	public String getRemoveResult() {
		return removeResult;
	}


	public void setRemoveResult(String removeResult) {
		this.removeResult = removeResult;
	}


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
		this.storeID = Long.parseLong(storeIDs.get(0));

		// get movies
		movies = movieRentalStore.getStoreMovies(storeID);
		this.movieNames = new ArrayList<String>();
		for (Movie movie : movies) {
			this.movieNames.add(movie.getTitle());
		}


		//get renterName
		this.renterName = renterNames.get(0);
		this.renterFirstName = renterName.split(" ")[0];
		this.renterLastName = renterName.split(" ")[1];
		
		this.movieName = movieNames.get(0);		

		updateRenterMovies(renterName.split(" ")[0], renterName.split(" ")[1]);
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


	public void updateRenterMovies(String lastname, String firstname) {
		// get movies
		renterMovies = movieRentalStore.getRentersMovies(firstname, lastname);
		this.renterMovieNames = new ArrayList<String>();
		for (Movie movie : renterMovies) {
			this.renterMovieNames.add(movie.getTitle());
		}

		if(renterMovieNames.size() > 0) this.renterMovieName = renterMovieNames.get(0);
		else this.renterMovieName = "No movies rented";
	}

	public String getRenterMovieName() {
		return renterMovieName;
	}


	public void setRenterMovieName(String renterMovieName) {
		this.renterMovieName = renterMovieName;
	}


	public void updateRenter(ValueChangeEvent event) {

		this.renterName = (String)event.getNewValue().toString();

		this.renterLastName = this.renterName.split(" ")[0];
		this.renterFirstName = this.renterName.split(" ")[1];

		updateRenterMovies(renterLastName, renterFirstName);
		

		//    	
		//	    List<Account> accounts = bank.getAccountListFromClientLastname(this.sourceClientName);
		//	    this.sourceAccountDescriptions = new ArrayList<String>();
		//		for (Account account : accounts) {
		//			this.sourceAccountDescriptions.add(account.getDescription());
		//		}
	}


	public void updateStore(ValueChangeEvent event) {
		this.storeID = (long)event.getNewValue();
		
		this.movies = movieRentalStore.getStoreMovies(storeID);

		this.movieNames = new ArrayList<String>();
		for (Movie movie : movies) {
			this.movieNames.add(movie.getTitle());
		}
		//			
		//	    List<Account> accounts = bank.getAccountListFromClientLastname(this.destinationClientName);
		//	    this.destinationAccountDescriptions = new ArrayList<String>();
		//		for (Account account : accounts) {
		//			this.destinationAccountDescriptions.add(account.getDescription());
		//		}
	}
	
	public void updateRenterMovie(ValueChangeEvent event) {
		this.renterMovieName = (String)event.getNewValue();
		//			
		//	    List<Account> accounts = bank.getAccountListFromClientLastname(this.destinationClientName);
		//	    this.destinationAccountDescriptions = new ArrayList<String>();
		//		for (Account account : accounts) {
		//			this.destinationAccountDescriptions.add(account.getDescription());
		//		}
	}

	public void updateMovie(ValueChangeEvent event) {
		this.movieName = (String)event.getNewValue();
		//			
		//	    List<Account> accounts = bank.getAccountListFromClientLastname(this.destinationClientName);
		//	    this.destinationAccountDescriptions = new ArrayList<String>();
		//		for (Account account : accounts) {
		//			this.destinationAccountDescriptions.add(account.getDescription());
		//		}
	}


	public String performRental() {

		try {
			Renter renter = movieRentalStore.getRenter(renterLastName, renterFirstName);
			Store store = movieRentalStore.getStore(storeID);
			Movie movie = movieRentalStore.getMovie(movieName);
			String employeeName = movieRentalStore.rentMovie(store, renter, movie);
			this.rentalResult=employeeName+" rented "+movie.getTitle()+" to "+renterFirstName+" "+renterLastName;
		} catch (Exception e) {
			this.rentalResult = "Wasn't able to rent movie";
			e.printStackTrace();
		}

		return "showRentalResult"; //  the String value returned represents the outcome used by the navigation handler to determine what page to display next.
	} 



	public String performReturnal() {

		try {
			Renter renter = movieRentalStore.getRenter(renterLastName, renterFirstName);
			Store store = movieRentalStore.getStore(storeID);
			Movie renterMovie = movieRentalStore.getMovie(renterMovieName);

			String employeeName = movieRentalStore.returnMovie(store, renter, renterMovie);
			this.rentalResult=renterFirstName+" "+renterLastName+" returned "+renterMovie.getTitle()+" to "+employeeName;
		} catch (Exception e) {
			this.rentalResult = "Wasn't able to return movie";
			e.printStackTrace();
		}

		return "showRentalResult"; //  the String value returned represents the outcome used by the navigation handler to determine what page to display next.
	} 
	
	public String performRemoveMovie() {

		try {
			Movie movieToRemove = movieRentalStore.getMovie(movieName);
			Store ownerStore = movieRentalStore.getStore(storeID);
			movieRentalStore.removeMovie(movieToRemove, ownerStore);
			this.removeResult=movieName+" has been removed from inventory of store " + storeID + ".";
		} catch (Exception e) {
			this.removeResult="unable to remove movie";
			e.printStackTrace();
		}

		return "showRemoveResult"; //  the String value returned represents the outcome used by the navigation handler to determine what page to display next.
	} 
}