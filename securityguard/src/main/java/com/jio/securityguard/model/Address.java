package com.jio.securityguard.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="ADDRESS")
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ADDRESS_ID")
	private Integer addressId;
	
	@Column(name="ADDRESS_1")
	private String address1;
	
	@Column(name="CITY")
	private String city;
	
	//@OneToOne(cascade=CascadeType.ALL)
	//@JoinColumn(name="guest_id",referencedColumnName="GUEST_ID")
	//@OneToOne(mappedBy="address")
	//private Guest guest;
	
	public Address() {}

	public Address(Integer addressId, String address1, String city) {
		super();
		this.addressId = addressId;
		this.address1 = address1;
		this.city = city;
	}
	
	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}
/*
	public Guest getGuest() {
		return guest;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}
*/
	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
