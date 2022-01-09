package ch.hevs.movierentalservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import ch.hevs.businessobject.Address;
import ch.hevs.businessobject.Category;
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
		System.out.println(lastNameRenter+firstNameRenter);
		Query query = em.createQuery("FROM Renter r WHERE r.lastname=:lastName AND r.firstname=:firstName");
		query.setParameter("firstName", firstNameRenter);
		query.setParameter("lastName", lastNameRenter);

		Renter renter = (Renter) query.getSingleResult();
		System.out.println("ID renter called from getRenter(): "+renter.getId());
		return renter;
	}

	@SuppressWarnings("unchecked")
	public List<Movie> getRentersMovies(String lastNameRenter, String firstNameRenter) {
		return (List<Movie>) em.createQuery("SELECT movies FROM Renter r WHERE r.lastname=:lastname AND r.firstname=:firstname").setParameter("lastname", lastNameRenter).setParameter("firstname", firstNameRenter).getResultList();
	}

	public String rentMovie(Store store, Renter renter, Movie movie) throws Exception {

		Employee employee = getRandomEmployee();

		System.out.println("ID Movie called from rentMovie(): " + renter.getId());
		System.out.println("ID Renter called from rentMovie(): " + movie.getId());
		System.out.println("ID Employee called from rentMovie(): " + employee.getId());

		em.persist(renter);
		em.persist(movie);
		em.persist(employee);
		em.persist(store);
		renter.rentMovie(movie);
		store.removeMovie(movie);
		
		return employee.getFirstname()+" "+employee.getLastname();
	}

	public String returnMovie(Store store, Renter renter, Movie movie) throws Exception {

		Employee employee = getRandomEmployee();

		System.out.println("ID Movie called from returnMovie(): " + renter.getId());
		System.out.println("ID Renter called from returnMovie(): " + movie.getId());
		System.out.println("ID Employee called from returnMovie(): " + employee.getId());

		em.persist(renter);
		em.persist(movie);
		em.persist(employee);
		em.persist(store);
		renter.returnMovie(movie);
		store.addMovie(movie);

		return employee.getFirstname()+" "+employee.getLastname();
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

	@Override
	public Movie getMovie(String title) {
		return (Movie) em.createQuery("FROM Movie m WHERE m.title=:title").setParameter("title", title).getSingleResult();
	}

	@Override
	public void removeMovie(Movie movieToRemove, Store ownerStore) {
		ownerStore.removeMovie(movieToRemove);
		em.persist(ownerStore);
	}

	@Override
	public List<Movie> getStoreMovies(long storeID) {
		return em.createQuery("Select s.movies FROM Store s where s.id=:storeID").setParameter("storeID", storeID).getResultList();
	}

	@Override
	public void populateDB() {
		
		if (getRenters().size() > 0) return;
		
		List<Category> lotrCategories = new ArrayList<Category>();
		Category fantasy = new Category("Fantasy");
		em.persist(fantasy);
		lotrCategories.add(fantasy);
		
		Movie lotr = new Movie("Lord of the Rings", 198, 2001, lotrCategories, "DVD");
		em.persist(lotr);
		
		List<Category> haniCategories = new ArrayList<Category>();
		Category horror = new Category("Horror");
		em.persist(horror);
		
		haniCategories.add(horror);		
		Movie hani = new Movie("Hanibal", 123, 2002, haniCategories, "BluRay");
		em.persist(hani);
		
		List<Category> silCategories = new ArrayList<Category>();
		Category thriller = new Category("Thriller");
		em.persist(thriller);
		Category drama = new Category("Drama");
		em.persist(drama);
		
		silCategories.add(thriller);
		silCategories.add(drama);
		silCategories.add(horror);
		Movie sil = new Movie("Silence of the Lambs", 119, 1996, silCategories, "VHS");
		em.persist(sil);
		
		List<Category> fcCategories = new ArrayList<Category>();
		fcCategories.add(drama);
		fcCategories.add(thriller);
		Movie fc = new Movie("Fight Club", 145, 1999, fcCategories, "VHS");
		em.persist(fc);
		
		List<Employee> employees1 = new ArrayList<Employee>();
		List<Employee> employees2 = new ArrayList<Employee>();
		
		Employee employee1 = new Employee("Hans", "Klaus", new Address(3902, "Glis", "Gliserallee 3"));
		Employee employee2 = new Employee("Peter", "Schmid", new Address(8001, "Zürich", "Bahnhofstrasse 5"));
		Employee employee3 = new Employee("Sven", "Hutter", new Address(4242, "Bern", "Geigerweg 42"));
		Employee employee4 = new Employee("Jonathan", "Albrecht", new Address(4242, "St. Gallen", "Bitteweg 42"));
		
		em.persist(employee1);
		em.persist(employee2);
		em.persist(employee3);
		em.persist(employee4);
		
		employees1.add(employee1);
		employees1.add(employee2);
		employees2.add(employee3);
		employees2.add(employee4);
		
		List<Movie> movies1 = new ArrayList<Movie>();
		movies1.add(lotr);
		movies1.add(hani);
		
		List<Movie> movies2 = new ArrayList<Movie>();
		movies2.add(fc);
		movies2.add(sil);	
		
		
		Store store1 = new Store(new Address(3930, "Visp", "Bahnhofstrasse 3"), movies1, employees1);
		Store store2 = new Store(new Address(8970, "Zürich", "Bahnhofstrasse 42"), movies2, employees2);
		em.persist(store1);
		em.persist(store2);
		
		
		Renter renter1 = new Renter(new ArrayList<Movie>(), "Daniel", "Abgottspon", new Address(3902, "Glis", "Gliserallee 75"));
		Renter renter2 = new Renter(new ArrayList<Movie>(),"Peter", "Schmid", new Address(8001, "Zürich", "Bahnhofstrasse 42"));
		Renter renter3 = new Renter(new ArrayList<Movie>(),"Maddin", "Schneider", new Address(4242, "Bern", "Geigerweg 56"));
		Renter renter4 = new Renter(new ArrayList<Movie>(),"Erasmus", "Ingeborg", new Address(4242, "St. Gallen", "Bitteweg 76"));
		em.persist(renter1);
		em.persist(renter2);
		em.persist(renter3);
		em.persist(renter4);
		
	}
	

	
}
