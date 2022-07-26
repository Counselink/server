package com.counselink.Counselink.service;

import com.counselink.Counselink.entity.member.Counselor;
import com.counselink.Counselink.repository.spring_data_jpa.CounselorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CounselorService {

    private final CounselorRepository counselorRepository;

    private boolean isSignupValid(String loginId) {
        Optional<Counselor> tmp = counselorRepository.findByLoginId(loginId);

        if (tmp.isPresent()) {
            return false;
        } else {
            return true;
        }
    }

    @Transactional
    public Counselor saveCounselor(Counselor counselor) {
        if (isSignupValid(counselor.getLoginId())) {
            return counselorRepository.saveAndFlush(counselor);
        } else {
            return null;
        }
    }
}
