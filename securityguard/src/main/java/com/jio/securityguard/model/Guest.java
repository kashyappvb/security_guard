package com.jio.securityguard.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="GUEST")
public class Guest {
	
	@Id
	@Column(name="GUEST_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer guestId;
	
	@Column(name="NAME")
	private String name;
	
	//@OneToOne(cascade=CascadeType.ALL)
	//@JoinColumn(name="address_id",referencedColumnName="ADDRESS_ID")
	//private Address address;

	@Enumerated(EnumType.STRING)
	@Column(name="GUEST_TYPE")
	private GuestType guestType;
	
	@Column(name="CONTACT_NUMBER")
	private String contactNumber;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_ON")
    private Date createdOn = new Date();
	
	@Column(name="RESIDENT_ID")
	private Integer residentId;
	
	@Column(name="GUEST_IMAGE_PATH")
	private String guestImagePath;
	
	public Guest() {}
	
	public Guest(Integer guestId, String name, GuestType guestType, String contactNumber, Date createdOn,
			Integer residentId, String guestImagePath) {
		super();
		this.guestId = guestId;
		this.name = name;
		this.guestType = guestType;
		this.contactNumber = contactNumber;
		this.createdOn = createdOn;
		this.residentId = residentId;
		this.guestImagePath = guestImagePath;
	}

	public Integer getGuestId() {
		return guestId;
	}

	public void setGuestId(Integer guestId) {
		this.guestId = guestId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/*
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	 */
	public GuestType getGuestType() {
		return guestType;
	}

	public void setGuestType(GuestType guestType) {
		this.guestType = guestType;
	}
	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	
	public Integer getResidentId() {
		return residentId;
	}

	public void setResidentId(Integer residentId) {
		this.residentId = residentId;
	}

	public String getGuestImagePath() {
		return guestImagePath;
	}

	public void setGuestImagePath(String guestImagePath) {
		this.guestImagePath = guestImagePath;
	}

	@Override
	public String toString() {
		return "Guest [guestId=" + guestId + ", name=" + name + ", guestType=" + guestType + ", contactNumber="
				+ contactNumber + ", createdOn=" + createdOn + ", residentId=" + residentId + ", guestImagePath="
				+ guestImagePath + "]";
	}
}
