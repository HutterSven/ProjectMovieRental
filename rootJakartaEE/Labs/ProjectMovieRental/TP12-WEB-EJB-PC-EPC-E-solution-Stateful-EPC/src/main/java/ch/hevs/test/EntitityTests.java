package ch.hevs.test;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.Test;

import ch.hevs.businessobject.Address;
import ch.hevs.businessobject.Store;
import ch.hevs.businessobject.Employee;
import ch.hevs.businessobject.Movie;
import ch.hevs.businessobject.Renter;
import ch.hevs.businessobject.Category;

public class EntitityTests {

	@Test
	public void test() {
		EntityTransaction tx = null;
		try {


			EntityManagerFactory emf = Persistence.createEntityManagerFactory("moviePU");
			EntityManager em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			Address address1 = new Address(3902, "Gliserallee 67", "Glis");
			Address address4 = new Address(3900, "Hansstrasse 45", "Brig");
			Address address5 = new Address(8000, "Zuberstrasse 3", "Zürich");
			Address address6 = new Address(6578, "Schmidtstrasse 47", "Basel");

			Renter c1 = new Renter(new ArrayList<Movie>(), "Platini", "Michel", address1);
			Renter c2 = new Renter(new ArrayList<Movie>(), "Schmid", "Ueli", address4);
			Renter c3 = new Renter(new ArrayList<Movie>(), "Muster", "Peter", address5);
			Renter c4 = new Renter(new ArrayList<Movie>(), "Andre", "Pandre", address6);

			em.persist(c1);
			em.persist(c4);
			em.persist(c3);
			em.persist(c2);

			ArrayList<Employee> employees = new ArrayList<Employee>();

			ArrayList<Movie> movies = new ArrayList<Movie>();

			ArrayList<Category> categories = new ArrayList<Category>();
			
			//TODO: change to adding category one by one to movie

			Category category = new Category("Fantasy");
			em.persist(category);

			Category category2 = new Category("Action");
			em.persist(category2);

			Category category3 = new Category("Drama");
			em.persist(category3);

			Category category4 = new Category("Thriller");
			em.persist(category4);
			
			Category category5 = new Category("Comedy");
			em.persist(category5);


			categories.add(category);
			categories.add(category2);

			Movie lotr = new Movie("Lord of the Rings", 168, 2001, categories);

			em.persist(lotr);
			
			ArrayList<Category> categories2 = new ArrayList<Category>();
			categories2.add(category5);
			categories2.add(category);

			Movie movie1 = new Movie("What we do in the Shadows", 120, 2001, categories2);

			em.persist(movie1);
			
			ArrayList<Category> categories3 = new ArrayList<Category>();
			categories3.add(category3);
			categories3.add(category2);
			
			Movie movie2 = new Movie("Clockwork Orange", 168, 1978, categories3);

			em.persist(movie2);
			
			ArrayList<Category> categories4 = new ArrayList<Category>();
			categories4.add(category2);
			categories4.add(category5);
			
			Movie movie3 = new Movie("Seven", 168, 2002, categories4);

			em.persist(movie3);
			
			ArrayList<Category> categories5 = new ArrayList<Category>();
			categories5.add(category);
			categories5.add(category3);
			
			Movie movie4 = new Movie("Hannibal", 168, 1999, categories5);

			em.persist(movie4);

			movies.add(lotr);

			Address address2 =  new Address(7500, "Gliser 23", "Allee");

			Employee em1 = new Employee("Hans", "Klausi", address2);
			Employee em2 = new Employee("Klaus", "Hansi", address2);
			Employee em3 = new Employee("Peter", "Muster", address2);
			Employee em4 = new Employee("John", "Johnson", address2);

			em.persist(em1);
			em.persist(em4);
			em.persist(em3);
			em.persist(em2);

			employees.add(em1);
			employees.add(em2);
			
			ArrayList<Employee> employees2 = new ArrayList<Employee>();
			
			employees2.add(em3);
			employees2.add(em4);

			Address address3 = new Address(3930, "Gliserallee 86", "Glis");
			Address address7 = new Address(8001, "Bahnhofstrasse 1", "Zürich");

			ArrayList<Movie> storeMovieList = new ArrayList<Movie>();

			storeMovieList.add(lotr);
			storeMovieList.add(movie4);
			
			ArrayList<Movie> storeMovieList2 = new ArrayList<Movie>();

			storeMovieList2.add(movie1);
			storeMovieList2.add(movie2);
			storeMovieList2.add(movie3);


			Store s1 = new Store(address3, storeMovieList, employees);
			Store s2 = new Store(address7, storeMovieList2, employees2);

			em.persist(s1);
			em.persist(s2);


			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
			/*
			 * try { tx.rollback(); } catch (IllegalStateException e1) {
			 * e1.printStackTrace(); } catch (SecurityException e1) {
			 * e1.printStackTrace(); } catch (SystemException e1) {
			 * e1.printStackTrace(); }
			 */
		}

	}
}
