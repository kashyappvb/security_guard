package com.jio.securityguard.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name="NOTIFICATION")
public class Notification {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="NOTIFICATION_ID")
	private Integer notificationId;
	
	@Column(name="RESIDENT_ID")
	private Integer residentId;
	
	@JsonProperty(access=Access.WRITE_ONLY)
	@Enumerated(EnumType.STRING)
	@Column(name="NOTIFICATION_STATUS")
	private NotificationStatus notificationStatus;

	@Column(name="NOTIFICATION_DESC")
	private String notificationDesc;
	
	public Notification() {}
	
	public Notification(Integer notificationId, Integer residentId, NotificationStatus notificationStatus,
			String notificationDesc) {
		super();
		this.notificationId = notificationId;
		this.residentId = residentId;
		this.notificationStatus = notificationStatus;
		this.notificationDesc = notificationDesc;
	}

	public Integer getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(Integer notificationId) {
		this.notificationId = notificationId;
	}

	public Integer getResidentId() {
		return residentId;
	}

	public void setResidentId(Integer residentId) {
		this.residentId = residentId;
	}

	public NotificationStatus getNotificationStatus() {
		return notificationStatus;
	}

	public void setNotificationStatus(NotificationStatus notificationStatus) {
		this.notificationStatus = notificationStatus;
	}

	public String getNotificationDesc() {
		return notificationDesc;
	}

	public void setNotificationDesc(String notificationDesc) {
		this.notificationDesc = notificationDesc;
	}

	@Override
	public String toString() {
		return "Notification [notificationId=" + notificationId + ", residentId=" + residentId + ", notificationStatus="
				+ notificationStatus + ", notificationDesc=" + notificationDesc + "]";
	}
	
	
}
