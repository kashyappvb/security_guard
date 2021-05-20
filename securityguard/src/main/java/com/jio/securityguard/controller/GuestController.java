package com.jio.securityguard.controller;

import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jio.securityguard.exception.CustomException;
import com.jio.securityguard.model.Guest;
import com.jio.securityguard.model.GuestResponse;
import com.jio.securityguard.service.GuestService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("appartement")
public class GuestController {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	String[] allowedExtension = {"jpg","png","jpeg"};

	@Autowired
	private GuestService guestService;
	
	
	@PostMapping(value="guest", consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
	public GuestResponse saveGuest(
			@RequestPart(value="file") MultipartFile file,
			@RequestPart(value="guest") Guest guest
			) {
		
		LOGGER.info("Inside saveGuest, {}",guest);
		validateInputFile(file);
		
		
		Guest guestObj = guestService.saveGuest(file, guest);
        GuestResponse guestResponseObject = new GuestResponse();
        guestResponseObject.setGuestId(guestObj.getGuestId());
        guestResponseObject.setGuestImagePath(guestObj.getGuestImagePath());
        
        LOGGER.info("Exit saveGuest, {}",guest);
        
		return guestResponseObject;
	}
	
	private boolean validateInputFile(MultipartFile file)
	{
		int index = file.getOriginalFilename().lastIndexOf('.');
		if(index > 0) {
	      String extension = file.getOriginalFilename().substring(index + 1);
	      if(Arrays.stream(allowedExtension).noneMatch( ext -> ext.equalsIgnoreCase(extension)))
	    	  throw new CustomException("Allowed File extension: " + Arrays.toString(allowedExtension));
		}
		
		return true;
		
	}
}
