package com.group06.JobFairApp.repository;

import com.group06.JobFairApp.model.Booth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoothRepository extends JpaRepository<Booth, Long> {
    Booth findByBoothNumber(int boothNumber);
}
