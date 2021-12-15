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
			
			Address address1 = new Address(3930, "Gliserallee", "Glis");
	
			Renter c1 = new Renter(new ArrayList<Movie>(), "Platini", "Michel", address1);

			em.persist(c1);
			
			ArrayList<Employee> employees = new ArrayList<Employee>();
			
			ArrayList<Movie> movies = new ArrayList<Movie>();
			
			ArrayList<Category> categories = new ArrayList<Category>();
			
			Category category = new Category("Fantasy");
			em.persist(category);
			
			Category category2 = new Category("Best movie ever and Sven what are you doing with your life not watching it");
			em.persist(category2);
			
			categories.add(category);
			categories.add(category2);
			
			Movie lotr = new Movie("Lord of the Rings", 168, 2001, categories);
			
			em.persist(lotr);
			
			movies.add(lotr);
			
			Address address2 =  new Address(7500, "Gliser", "Allee");
			
			Employee em1 = new Employee("Hans", "Klausi", address2);
			
			em.persist(em1);
			
			employees.add(em1);
			
			Address address3 = new Address(3930, "Gliserallee", "Glis");

			ArrayList<Movie> storeMovieList = new ArrayList<Movie>();
			
			storeMovieList.add(lotr);
			
			
			Store s1 = new Store(address3, new ArrayList<Movie>(), employees);
			
			em.persist(s1);
			
			
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
