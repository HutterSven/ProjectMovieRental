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
    private String rentalResult;
    private MovieRentalStore movieRentalStore;
    private String renterFirstName;
    private String renterLastName;
    private long storeID;
    private Movie movie;
    
    @PostConstruct
    public void initialize() throws NamingException {
    	
    	// use JNDI to inject reference to bank EJB
    	InitialContext ctx = new InitialContext();
		movieRentalStore = (MovieRentalStore) ctx.lookup("java:global/TP12-WEB-EJB-PC-EPC-E-0.0.1-SNAPSHOT/MovieRentalStoreBean!ch.hevs.movierentalservice.Movie");
			
    	// get renters
		renters = new ArrayList<Renter>();
		this.renterNames = new ArrayList<String>();
		for (Renter renter : renters) {
			this.renterNames.add(renter.getLastname());
		}
		
		
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
//    	this.sourceClientName = (String)event.getNewValue();
//    	
//	    List<Account> accounts = bank.getAccountListFromClientLastname(this.sourceClientName);
//	    this.sourceAccountDescriptions = new ArrayList<String>();
//		for (Account account : accounts) {
//			this.sourceAccountDescriptions.add(account.getDescription());
//		}
    }
	public void updateStore(ValueChangeEvent event) {
//    	this.destinationClientName = (String)event.getNewValue();
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
}