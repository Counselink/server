package com.counselink.Counselink.service;

import com.counselink.Counselink.entity.CounselInformation;
import com.counselink.Counselink.entity.Reserve;
import com.counselink.Counselink.entity.member.User;
import com.counselink.Counselink.repository.CounselInformationRepository;
import com.counselink.Counselink.repository.ReserveRepository;
import com.counselink.Counselink.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReserveService {

    private final UserRepository userRepository;
    private final CounselInformationRepository counselInformationRepository;
    private final ReserveRepository reserveRepository;

    /**
     * 예약
     */
    @Transactional
    public long reserve(Long userId, List<Long> counselInformationId) {

        User user = userRepository.findById(userId).get();

        List<CounselInformation> counselInformationList = new ArrayList<>();

        for (Long id : counselInformationId) {
            CounselInformation counselInformation = counselInformationRepository.findById(id).get();
            counselInformationList.add(counselInformation);
        }

        // 예시는 하나만 함(가변인자), JPA활용 - 주문서비스개발 9분
        Reserve reserve = Reserve.createReserve(user, counselInformationList);

        reserveRepository.save(reserve);

        return reserve.getId();

    }

    /**
     * 예약 취소
     */
    @Transactional
    public void cancelReserve(Long reserveId) {
        Reserve reserve = reserveRepository.findById(reserveId).get();
        reserve.cancel();
    }

}
