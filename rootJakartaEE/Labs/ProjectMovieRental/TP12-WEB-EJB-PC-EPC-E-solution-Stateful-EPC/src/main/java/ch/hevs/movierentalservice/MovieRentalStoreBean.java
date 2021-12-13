package ch.hevs.movierentalservice;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import ch.hevs.businessobject.Movie;
import ch.hevs.businessobject.Renter;
import ch.hevs.businessobject.Store;

@Stateful
public class MovieRentalStoreBean implements MovieRentalStore {
	
	@PersistenceContext(name = "BankPU", type=PersistenceContextType.EXTENDED)
	private EntityManager em;

	@Override
	public Renter getRenter(Renter renter) {
		return renter;
		// TODO Auto-generated method stub
		
//		Query query = em.createQuery("FROM Renter a WHERE a.lastName=:lastName AND a.owner.lastname=:lastname");
//		query.setParameter("description", accountDescription);
//		query.setParameter("lastname", lastnameOwner);
//		
//		Account account = (Account) query.getSingleResult();
//		System.out.println("ID account called from getAccount(): "+account.getId());
//		return account;
	}

	@Override
	public List<Movie> getRentersMovies(String lastname) {
		return null;

//		return (List<Account>) em.createQuery("SELECT c.accounts FROM Client c where c.lastname=:lastname").setParameter("lastname", lastname).getResultList();
	}

	@Override
	public void transfer(Store store, Renter renter, Movie movie) throws Exception {
		
//		System.out.println("ID source account called from transfer(): " + srcAccount.getId());
//		System.out.println("ID destination account called from transfer(): " + destAccount.getId());
//		
//		em.persist(srcAccount);
//		em.persist(destAccount);
//		srcAccount.debit(amount);
//		destAccount.credit(amount); 
		
	}

	@Override
	public List<Renter> getRenters() {
		return null;
//		return em.createQuery("FROM Client").getResultList();
	}

	@Override
	public Renter getClient(long renterId) {
		return null;
//		return (Client) em.createQuery("FROM Client c where c.id=:id").setParameter("id", clientid).getSingleResult();
	}
}
