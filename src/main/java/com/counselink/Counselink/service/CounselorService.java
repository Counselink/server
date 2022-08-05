package com.counselink.Counselink.service;

import com.counselink.Counselink.entity.member.Counselor;
import com.counselink.Counselink.repository.counselor.CounselorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CounselorService {

    private final CounselorRepository counselorRepository;

    private boolean isSignupValid(String loginId) {
        Optional<Counselor> tmp = counselorRepository.findByLoginId(loginId);

        return tmp.isEmpty();
    }

    @Transactional
    public void isLoginValidV2(Counselor counselor) throws Exception {
        Optional<Counselor> counselors = counselorRepository.findByEmailAndLoginPassword(counselor.getEmail(), counselor.getLoginPassword());
        if (!counselors.isPresent()) {
            throw new Exception("login is not valid");
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
