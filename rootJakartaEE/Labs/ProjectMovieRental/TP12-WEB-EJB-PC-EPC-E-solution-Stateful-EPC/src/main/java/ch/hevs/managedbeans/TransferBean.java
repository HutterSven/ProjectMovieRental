package ch.hevs.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ValueChangeEvent;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.hevs.businessobject.Movie;
import ch.hevs.businessobject.Renter;
import ch.hevs.movierentalservice.MovieRentalStore;

/**
 * TransferBean.java
 * 
 */

public class TransferBean
{
    private List<Renter> renters;
    private List<String> renterNames;
    private String transactionResult;
    private Movie movie;
    private Store store;
    private Renter renter;
    
    @PostConstruct
    public void initialize() throws NamingException {
    	
    	// use JNDI to inject reference to bank EJB
    	InitialContext ctx = new InitialContext();
		store = (Store) ctx.lookup("java:global/TP12-WEB-EJB-PC-EPC-E-0.0.1-SNAPSHOT/MovieRentalStoreBean!ch.hevs.movierentalservice.Movie");
			
    	// get clients
		List<Renter> renters = ;
		this.renterNames = new ArrayList<String>();
		for (Renter renter : renters) {
			this.renterNames.add(renter.getLastname());
		}
		
		// initialize account descriptions
		List<Renter> accounts = bank.getAccountListFromClientLastname(clientList.get(0).getLastname());
    }
    
    // transactionAmount
    public Movie getMovieRented () {
    	return movie;
    }
    public void setMovieRented (final Movie movie) {
    	this.movie = movie;
    }
    
    // transactionResult
    public String getTransactionResult () {
    	return transactionResult;
    }
	public void setTransactionResult(String transactionResult) {
		this.transactionResult = transactionResult;
	}
    

	public void updateSourceAccounts(ValueChangeEvent event) {
    	this.sourceClientName = (String)event.getNewValue();
    	
	    List<Account> accounts = bank.getAccountListFromClientLastname(this.sourceClientName);
	    this.sourceAccountDescriptions = new ArrayList<String>();
		for (Account account : accounts) {
			this.sourceAccountDescriptions.add(account.getDescription());
		}
    }
	public void updateDestinationAccounts(ValueChangeEvent event) {
    	this.destinationClientName = (String)event.getNewValue();
			
	    List<Account> accounts = bank.getAccountListFromClientLastname(this.destinationClientName);
	    this.destinationAccountDescriptions = new ArrayList<String>();
		for (Account account : accounts) {
			this.destinationAccountDescriptions.add(account.getDescription());
		}
    }
    
    public String performTransfer() {
    	
    	try {
			if (sourceClientName.equals(destinationClientName) && sourceAccountDescription.equals(destinationAccountDescription)) {
				
				this.transactionResult="Error: accounts are identical!";
			} 
			else {
				
				Account compteSrc = bank.getAccount(sourceAccountDescription, sourceClientName);
				Account compteDest = bank.getAccount(destinationAccountDescription, destinationClientName);
	
				// Transfer
				bank.transfer(compteSrc, compteDest, transactionAmount);
				this.transactionResult="Success!";
			}
    	} catch (Exception e) {
    		e.printStackTrace();
    	}

		return "showTransferResult"; //  the String value returned represents the outcome used by the navigation handler to determine what page to display next.
	} 
}