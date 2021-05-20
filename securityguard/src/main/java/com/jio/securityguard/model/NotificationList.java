package com.jio.securityguard.model;

import java.util.List;

public class NotificationList {
	
	private List<Notification> newNotification;
	
	private List<Notification> oldNotification;

	public List<Notification> getNewNotification() {
		return newNotification;
	}

	public void setNewNotification(List<Notification> newNotification) {
		this.newNotification = newNotification;
	}

	public List<Notification> getOldNotification() {
		return oldNotification;
	}

	public void setOldNotification(List<Notification> oldNotification) {
		this.oldNotification = oldNotification;
	}

	@Override
	public String toString() {
		return "NotificationList [newNotification=" + newNotification + ", oldNotification=" + oldNotification + "]";
	}
	
}
