package com.counselink.Counselink.service;

import com.counselink.Counselink.entity.CounselInformation;
import com.counselink.Counselink.repository.CounselInformationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CounselInformationService {

    private final CounselInformationRepository counselInformationRepository;

    public List<CounselInformation> checkTime(String category, LocalDateTime counselDate, LocalDateTime startTime, LocalDateTime endTime) {

        return counselInformationRepository.
                findCounselInformationsByCategoryAndCounselDateAndCounselStartTimeBetween(
                        category,
                        counselDate,
                        startTime,
                        endTime
                );
    }
}
