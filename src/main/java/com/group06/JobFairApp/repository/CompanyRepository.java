package com.group06.JobFairApp.repository;

import com.group06.JobFairApp.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Company findByBoothNumber(int boothNumber);
    Company findByName(String name);
    Company findByWebsiteUrl(String websiteUrl);
}
