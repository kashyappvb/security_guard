package com.jio.securityguard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jio.securityguard.model.GuestList;
import com.jio.securityguard.model.NotificationList;
import com.jio.securityguard.service.ResidentService;

@RestController
@RequestMapping("resident")
public class ResidentController {

	@Autowired
	private ResidentService residentService;
	
	@GetMapping("{residentId}/guest-list")
	public GuestList getAllGuests(
			@PathVariable("residentId") Integer residentId,
			@RequestParam(value="page",defaultValue="1") Integer page,
			@RequestParam(value="size",defaultValue="5") Integer size,
			@RequestParam(value="from",required=false) String from,
			@RequestParam(value="to",required=false) String to
			)
	{
		if(page>0)
			page=page-1;
		
		Pageable paging = PageRequest.of(page, size);
		return residentService.getGuestsForResident(residentId,paging,from,to);
	}
	
	@GetMapping("{residentId}/notification")
	public NotificationList getNotification(
			@PathVariable("residentId") Integer residentId
			)
	{
		return residentService.getNotificationForResident(residentId);
	}
}
