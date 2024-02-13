package com.example.lawSearch.global.email.schedule;

import com.example.lawSearch.global.email.repository.CertificationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class EmailCertificationSchedule {

    private final CertificationRepository certificationRepository;

    @Scheduled(cron = "0 59 23 * * *")
    @Transactional
    public void deleteExpiredCertification() {
        LocalDateTime startTime = LocalDateTime.now().withHour(0).withMinute(0);
        LocalDateTime endTime = LocalDateTime.now().withHour(23).withMinute(54);
        certificationRepository.deleteAllByCreatedDateBetween(startTime, endTime);
    }
}
