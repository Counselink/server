package com.counselink.Counselink.login;

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

    @Transactional
    public void isLoginValid(Counselor counselor) throws Exception {
        Optional<Counselor> counselors = counselorRepository.findByEmail(counselor);
        if (!counselors.isPresent()) {throw new Exception("login is not valid");}
    }
}
