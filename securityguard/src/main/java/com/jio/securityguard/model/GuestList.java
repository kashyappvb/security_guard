package com.jio.securityguard.model;

import java.util.List;

public class GuestList {
	
	public List<Guest> visitor;
	
	public List<Guest> delivery;

	public List<Guest> getVisitor() {
		return visitor;
	}

	public void setVisitor(List<Guest> visitor) {
		this.visitor = visitor;
	}

	public List<Guest> getDelivery() {
		return delivery;
	}

	public void setDelivery(List<Guest> delivery) {
		this.delivery = delivery;
	}

	@Override
	public String toString() {
		return "GuestList [visitor=" + visitor + ", delivery=" + delivery + "]";
	}
}
