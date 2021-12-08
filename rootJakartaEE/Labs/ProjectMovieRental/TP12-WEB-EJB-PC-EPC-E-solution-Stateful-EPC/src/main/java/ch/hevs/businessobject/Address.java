package ch.hevs.businessobject;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;

@Embeddable
@Table(name="Address")
public class Address {
	
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getPostal() {
		return postal;
	}
	public void setPostal(int postal) {
		this.postal = postal;
	}
	@Column(name="Street")
	private String street;
	@Column(name="City")
	private String city;
	@Column(name="Postal")
	private int postal;
	

	
	// constructors
	public Address() {
	}
	public Address(String city, int postal, String street) {
		this.city = city;
		this.postal = postal;
		this.street = street;
	}
}
