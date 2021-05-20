package com.jio.securityguard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jio.securityguard.model.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
	
	public List<Notification> findByResidentId(Integer residentId);
}
