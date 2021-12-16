package ch.hevs.movierentalservice;

import java.util.List;
import java.util.Random;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import ch.hevs.businessobject.Employee;
import ch.hevs.businessobject.Movie;
import ch.hevs.businessobject.Renter;
import ch.hevs.businessobject.Store;

@Stateful
public class MovieRentalStoreBean implements MovieRentalStore {

	@PersistenceContext(name = "moviePU", type=PersistenceContextType.EXTENDED)
	private EntityManager em;

	@Override
	public Renter getRenter(String lastNameRenter, String firstNameRenter) {
		Query query = em.createQuery("FROM Renter r WHERE r.lastName=:lastName AND r.firstName=:firstName");
		query.setParameter("firstName", firstNameRenter);
		query.setParameter("lastName", lastNameRenter);

		Renter renter = (Renter) query.getSingleResult();
		System.out.println("ID renter called from getRenter(): "+renter.getId());
		return renter;
	}

	@SuppressWarnings("unchecked")
	public List<Movie> getRentersMovies(String lastNameRenter, String firstNameRenter) {
		return (List<Movie>) em.createQuery("SELECT movies FROM Renter r WHERE r.lastName=:lastName AND r.firstName=:firstName").setParameter("lastname", lastNameRenter).setParameter("firstname", firstNameRenter).getResultList();
	}

	public void rentMovie(Store store, Renter renter, Movie movie) throws Exception {

		Employee employee = getRandomEmployee();

		System.out.println("ID Movie called from rentMovie(): " + renter.getId());
		System.out.println("ID Renter called from rentMovie(): " + movie.getId());
		System.out.println("ID Employee called from rentMovie(): " + employee.getId());

		em.persist(renter);
		em.persist(movie);
		em.persist(employee);
		renter.rentMovie(movie);
		store.rentMovie(movie);
	}

	public void returnMovie(Store store, Renter renter, Movie movie) throws Exception {

		Employee employee = getRandomEmployee();

		System.out.println("ID Movie called from returnMovie(): " + renter.getId());
		System.out.println("ID Renter called from returnMovie(): " + movie.getId());
		System.out.println("ID Employee called from returnMovie(): " + employee.getId());

		em.persist(renter);
		em.persist(movie);
		em.persist(employee);
		renter.returnMovie(movie);
		store.returnMovie(movie);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Renter> getRenters() {
		return em.createQuery("FROM Renter").getResultList();
	}

	public Employee getRandomEmployee() {
		Employee employee;
		List<Employee> employees = getEmployees();

		Random r = new Random();
		employee = employees.get(r.nextInt(employees.size()));

		return employee;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getEmployees() {
		return em.createQuery("FROM Employee").getResultList();
	}

	@Override
	public Store getStore(long storeID) {
		return (Store) em.createQuery("FROM Store s WHERE s.id=:id").setParameter("id", storeID).getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Store> getStores() {
		return em.createQuery("FROM Store").getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Movie> getMovies() {
		return em.createQuery("FROM Movie").getResultList();
	}
}
