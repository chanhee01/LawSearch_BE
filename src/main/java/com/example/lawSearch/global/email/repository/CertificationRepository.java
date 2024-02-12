package com.example.lawSearch.global.email.repository;

import com.example.lawSearch.global.email.model.CertificationEmail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface CertificationRepository extends JpaRepository<CertificationEmail, Long> {
    void deleteAllByCreatedDateBetween(LocalDateTime startTime, LocalDateTime endTime);
}
