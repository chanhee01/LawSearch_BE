package com.example.lawSearch.global.email.repository;

import com.example.lawSearch.global.email.model.CertificationEmail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificationRepository extends JpaRepository<CertificationEmail, Long> {
    CertificationEmail findByEmail(String email);
}
