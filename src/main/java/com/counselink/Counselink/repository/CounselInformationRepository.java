package com.counselink.Counselink.repository;

import com.counselink.Counselink.entity.CounselInformation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CounselInformationRepository extends JpaRepository<CounselInformation, Long> {

    List<CounselInformation> findCounselInformationsByCategoryAndCounselDateAndCounselStartTimeBetween(String category, LocalDateTime counselDate, LocalDateTime counselStartTime, LocalDateTime counselStartTime2);
}
