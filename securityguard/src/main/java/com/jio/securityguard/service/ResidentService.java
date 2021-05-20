package com.jio.securityguard.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jio.securityguard.exception.CustomException;
import com.jio.securityguard.model.Guest;
import com.jio.securityguard.model.GuestList;
import com.jio.securityguard.model.GuestType;
import com.jio.securityguard.model.Notification;
import com.jio.securityguard.model.NotificationList;
import com.jio.securityguard.model.NotificationStatus;
import com.jio.securityguard.repository.NotificationRepository;

@Service
public class ResidentService {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private GuestService guestService;
	
	@Autowired
	private NotificationRepository notificationRepository;
	
	public GuestList getGuestsForResident(Integer residentId,Pageable paging,String from,String to) 
	{
		LOGGER.info("Inside getGuestsForResident");
		LOGGER.info("resident id {}, page number {} and page size {}",residentId,paging.getPageNumber(),paging.getPageSize());
		
		Date fromDate = null;
		Date toDate = null;
		List<Guest> guestList = new ArrayList<Guest>();
		
		if(from!=null)
		{
			 try {
			
	            fromDate = new SimpleDateFormat("dd-MM-yyyy").parse(from);
				
				if(to==null)
				 {
						 toDate = new Date();
				 }
				 else
				 {
					 toDate = new SimpleDateFormat("dd-MM-yyyy").parse(to);
					 Calendar cal = Calendar.getInstance();
					 cal.setTime(toDate);
					 cal.set(Calendar.HOUR_OF_DAY, 23);
					 cal.set(Calendar.MINUTE, 59);
					 cal.set(Calendar.SECOND, 59);
					 toDate = cal.getTime();
				 }
				if(toDate.compareTo(fromDate) < 0)
				{
					throw new CustomException("To Date : " + toDate + " should be ahead of From date : " +fromDate);
				}
				
				LOGGER.info("finding guests between From {} , To {} ",fromDate,toDate);
				guestList = guestService.getGuestsForResidentBetweenDate(residentId, fromDate, toDate, paging);
			 } catch (ParseException e) {
				 LOGGER.error("Parse exception : " + e);
			 }  
			 
			 
		}
		else
		{
			guestList = guestService.getGuestsForResident(residentId,paging);
		}
		
		List<Guest> visitor = guestList.stream().filter(guest -> guest.getGuestType().toString().equals(GuestType.VISITOR.toString())).collect(Collectors.toList());
		List<Guest> delivery = guestList.stream().filter(guest -> guest.getGuestType().toString().toString().equals(GuestType.DELIVERY.toString())).collect(Collectors.toList());
		GuestList guestListObject = new GuestList();
		guestListObject.setDelivery(delivery);
		guestListObject.setVisitor(visitor);
		
		LOGGER.debug("Guest List : {} ",guestListObject);
		
		LOGGER.info("END getGuestsForResident");
		return guestListObject;
	}
	
	public NotificationList getNotificationForResident(Integer residentId)
	{
		LOGGER.info("Inside getNotificationForResident");
		
		List<Notification> notificationList = notificationRepository.findByResidentId(residentId);
		
		List<Notification> newNotification = notificationList.stream().filter(notificationNew -> notificationNew.getNotificationStatus().toString().equalsIgnoreCase(NotificationStatus.NEW.toString())).collect(Collectors.toList());
		List<Notification> viewedNotification = notificationList.stream().filter(notificationOld -> notificationOld.getNotificationStatus().toString().equalsIgnoreCase(NotificationStatus.VIEWED.toString())).collect(Collectors.toList());
		
		NotificationList notificationListObject = new NotificationList();
		notificationListObject.setNewNotification(newNotification);
		notificationListObject.setOldNotification(viewedNotification);
		
		LOGGER.debug("Notification List : {}", notificationListObject);
		
		newNotification.forEach(notification -> notification.setNotificationStatus(NotificationStatus.VIEWED));
		
		notificationRepository.saveAll(notificationList);
		
		LOGGER.info("END getNotificationForResident");
		return notificationListObject;
	}
}
