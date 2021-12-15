package ch.hevs.businessobject;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Movie")
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	@Column(name="title")
	private String title;
	@Column(name="length")
	private int length;
	@Column(name="year")
	private int year;
	@Column(name="medium")
	private String Medium;
	
	@OneToMany
	private List<Category> categories;
	
	public Movie() {
		categories = new ArrayList<Category>();
	}
	public Movie(String title, int length, int year, List<Category> categories) {
		this.title = title;
		this.length = length;
		this.year = year;
		this.categories = categories;
	}
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	public List<Category> getCategories() {
		return categories;
	}
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	
	//TODO: add addCategory method
	
}
