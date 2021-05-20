package com.jio.securityguard.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;

import org.hibernate.bytecode.spi.NotInstrumentedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.jio.securityguard.exception.CustomException;
import com.jio.securityguard.exception.FileStorageException;
import com.jio.securityguard.model.Guest;
import com.jio.securityguard.model.Notification;
import com.jio.securityguard.model.NotificationStatus;
import com.jio.securityguard.repository.GuestRepository;
import com.jio.securityguard.repository.NotificationRepository;
import com.jio.securityguard.repository.ResidentRepository;

@Service
public class GuestService {
	
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private GuestRepository guestRepository;
	
	@Autowired
	private ResidentRepository residentRepository;
	
	@Autowired
	private NotificationRepository notificationRepository;
	
	@Value("${app.upload.dir:${user.home}}")
    public String uploadDir;
	
	public Guest saveGuest(MultipartFile file,Guest guest)
	{
		
		String guestImagePath = saveFile(file);
		LOGGER.info("Guest Image Path : {} ",guestImagePath);
		
		if(!residentRepository.findById(guest.getResidentId()).isPresent())
		{
			throw new CustomException("Resident with ID : " + guest.getResidentId() + " is not present!");
		}
		
		guest.setGuestImagePath(guestImagePath);
		Guest guestObject =  guestRepository.saveAndFlush(guest);
		
		/** IMPLEMENTING NOTIFICATION LOGIC
		 * 
		 * After guest is logged successfully, NOTIFICATION Table will have below object information
		 * 
		 * /resident/{residentId}/notification will get as NEW notification, once notification is seen, it moves to VIEWED 
		 * Ex: Guest 1 is seen as NEW Notification when /resident/1/notification is hit,
		 * 	   Guest 2, Guest 3 is logged before /resident/1/notification is called.
		 * 	   Guest 1 comes under oldNotification Array and Guest 2, Guest3 comes under new Notification Array (/resident/1/notification)
		 * 
		 * **/
		if(guestObject!=null)
		{
			Notification notification = new Notification();
			notification.setResidentId(guestObject.getResidentId());
			notification.setNotificationStatus(NotificationStatus.NEW);
			notification.setNotificationDesc("Guest " + guest.getName() + "(" + guest.getGuestType() + ")" + " wants to meet you");

			notificationRepository.saveAndFlush(notification);
		}
		guestObject.setGuestImagePath(guestImagePath);
		return guestObject;
	}
	
	public List<Guest> getGuestsForResident(Integer residentId,Pageable pageable)
	{
		return guestRepository.findByResidentId(residentId, pageable);
	}
	
	public List<Guest> getGuestsForResidentBetweenDate(Integer residentId,Date fromDate,Date toDate,Pageable pageable)
	{
		return guestRepository.findByResidentIdBetweenDates(residentId,fromDate,toDate,pageable);
	}
	
	private String saveFile(MultipartFile file)
	{
		try {
        	Path copyLocation = Paths
                    .get(uploadDir + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));
			Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
			
			return copyLocation.toString();
		} catch (IOException e) {
			e.printStackTrace();
            throw new FileStorageException("Could not store file " + file.getOriginalFilename()
                + ". Please try again!");
		}
	}
}
