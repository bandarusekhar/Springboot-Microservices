package com.sekhar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sekhar.entity.Organization;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {

}
