package com.jio.securityguard.model;

public class GuestResponse {
	
	private Integer guestId;
	
	private String guestImagePath;

	public Integer getGuestId() {
		return guestId;
	}

	public void setGuestId(Integer guestId) {
		this.guestId = guestId;
	}

	public String getGuestImagePath() {
		return guestImagePath;
	}

	public void setGuestImagePath(String guestImagePath) {
		this.guestImagePath = guestImagePath;
	}

	@Override
	public String toString() {
		return "GuestResponse [guestId=" + guestId + ", guestImagePath=" + guestImagePath + "]";
	}
	
	
}
