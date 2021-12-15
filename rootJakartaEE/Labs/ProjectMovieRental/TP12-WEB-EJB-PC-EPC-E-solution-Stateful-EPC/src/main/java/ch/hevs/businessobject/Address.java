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
	@Column(name="street")
	private String street;
	@Column(name="city")
	private String city;
	@Column(name="postal")
	private int postal;
	

	
	// constructors
	public Address() {
	}
	public Address(int postal, String city, String street) {
		this.city = city;
		this.postal = postal;
		this.street = street;
	}
}
