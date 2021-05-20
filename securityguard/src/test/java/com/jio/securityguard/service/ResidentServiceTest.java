package com.jio.securityguard.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.jio.securityguard.model.Notification;
import com.jio.securityguard.model.NotificationList;
import com.jio.securityguard.model.NotificationStatus;
import com.jio.securityguard.repository.NotificationRepository;

public class ResidentServiceTest {

	@InjectMocks
	private ResidentService residentService;
	
	@Mock
	private NotificationRepository notificationRepository;
	
	@Before
    public void init() {
        MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetNotificationForResident()
	{
		Notification notification1 = new Notification();
		notification1.setResidentId(1);
		notification1.setNotificationId(1);
		notification1.setNotificationStatus(NotificationStatus.NEW);
		notification1.setNotificationDesc("Guest 1 (VISITOR) wants to meet you");
		
		Notification notification2 = new Notification();
		notification2.setResidentId(2);
		notification2.setNotificationId(2);
		notification2.setNotificationStatus(NotificationStatus.NEW);
		notification2.setNotificationDesc("Guest 2 (VISITOR) wants to meet you");
		
		Notification notification3 = new Notification();
		notification3.setResidentId(3);
		
		notification3.setNotificationId(3);
		notification3.setNotificationStatus(NotificationStatus.VIEWED);
		notification3.setNotificationDesc("Guest 3 (VISITOR) wants to meet you");
		
		List<Notification> notificationList = new ArrayList<>();
		notificationList.add(notification1);
		notificationList.add(notification2);
		notificationList.add(notification3);
		
		when(notificationRepository.findByResidentId(1)).thenReturn(notificationList);
		when(notificationRepository.saveAll(notificationList)).thenReturn(notificationList);
		
		NotificationList notificationListObject = residentService.getNotificationForResident(1);
		
		assertEquals(2, notificationListObject.getNewNotification().size());
		assertEquals(1, notificationListObject.getOldNotification().size());
		
		notificationListObject = residentService.getNotificationForResident(2);
		assertEquals(0, notificationListObject.getOldNotification().size());
		assertEquals(0, notificationListObject.getNewNotification().size());
	}
}
