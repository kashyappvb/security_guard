package com.jio.securityguard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jio.securityguard.model.Resident;

public interface ResidentRepository extends JpaRepository<Resident, Integer> {

}
