package com.jio.securityguard.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="RESIDENT")
public class Resident {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="RESIDENT_ID")
	private Integer residentId;
	
	@Column(name="RESIDENT_NAME")
	private String residentName;
	
	public Resident() {}
	
	public Resident(Integer residentId, String residentName) {
		super();
		this.residentId = residentId;
		this.residentName = residentName;
	}

	public Integer getResidentId() {
		return residentId;
	}

	public void setResidentId(Integer residentId) {
		this.residentId = residentId;
	}

	public String getResidentName() {
		return residentName;
	}

	public void setResidentName(String residentName) {
		this.residentName = residentName;
	}

	@Override
	public String toString() {
		return "Resident [residentId=" + residentId + ", residentName=" + residentName + "]";
	}
}
