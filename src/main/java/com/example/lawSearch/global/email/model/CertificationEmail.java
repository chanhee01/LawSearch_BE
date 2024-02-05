package com.example.lawSearch.global.email.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@NoArgsConstructor
public class CertificationEmail {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long Id;

    private String email;

    private String certificationNumber;

    @Builder
    public CertificationEmail(String email, String certificationNumber) {
        this.email = email;
        this.certificationNumber = certificationNumber;
    }
}
