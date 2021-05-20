package com.jio.securityguard.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jio.securityguard.model.Guest;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Integer> {
	
	public List<Guest> findByResidentId(Integer residentId,Pageable pageable);
	
	@Query("SELECT g FROM Guest g where residentId=?1 AND createdOn BETWEEN ?2 AND ?3")
	public List<Guest> findByResidentIdBetweenDates(Integer residentId,Date startDate,Date endDate,Pageable pageable);
}
