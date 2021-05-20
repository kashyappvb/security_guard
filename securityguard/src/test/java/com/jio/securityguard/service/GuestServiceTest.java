package com.jio.securityguard.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.jio.securityguard.model.Guest;
import com.jio.securityguard.model.GuestType;
import com.jio.securityguard.repository.GuestRepository;
import com.jio.securityguard.repository.ResidentRepository;

public class GuestServiceTest {

	@InjectMocks
	GuestService guestService;
	
	@Mock
	GuestService guestServiceMock;
	
	@Mock
	GuestRepository guestRepository;
	
	@Mock
	ResidentRepository residentRepository;
	
	@Mock
	Pageable pageable;
	
	@Mock
	MultipartFile file;
	
	Guest guest2 = new Guest();
	Guest guest1 = new Guest();
	
	@Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        
        
		guest1.setContactNumber("+91 1234567899");
		guest1.setName("Visitor 1");
		guest1.setResidentId(1);
		guest1.setCreatedOn(new Date());
		guest1.setGuestType(GuestType.VISITOR);
		guest1.setGuestImagePath("/test/image/path/image1.jpg");
		

		
		guest2.setContactNumber("+91 1111155555");
		guest2.setName("Visitor 2");
		guest2.setResidentId(1);
		guest2.setCreatedOn(new Date());
		guest2.setGuestType(GuestType.DELIVERY);
		guest2.setGuestImagePath("/test/image/path/image2.jpg");
    }
 
	
	@Test
	public void testGetGuestsForResident()
	{
		List<Guest> guests = new ArrayList<Guest>();
		
		guests.add(guest1);
		
		when(guestRepository.findByResidentId(1,pageable)).thenReturn(guests);
		
		List<Guest> guestReturned = guestService.getGuestsForResident(1, pageable);
		assertEquals(1, guestReturned.size());
	}
	
	@Test
	public void testSaveGuest()
	{
		List<Guest> guests = new ArrayList<Guest>();
		guests.add(guest1); guests.add(guest2);
		when(guestRepository.findByResidentIdBetweenDates(1, new Date(), new Date(), pageable)).thenReturn(guests);
		List<Guest> guestReturned = guestService.getGuestsForResidentBetweenDate(1, new Date(), new Date(), pageable);
		
		assertEquals(2, guestReturned.size());
		
	}
	
}
