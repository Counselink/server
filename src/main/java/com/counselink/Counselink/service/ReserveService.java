package com.counselink.Counselink.service;

import com.counselink.Counselink.entity.CounselInformation;
import com.counselink.Counselink.entity.Reserve;
import com.counselink.Counselink.entity.member.User;
import com.counselink.Counselink.repository.CounselInformationJpaRepository;
import com.counselink.Counselink.repository.ReserveJpaRepository;
import com.counselink.Counselink.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReserveService {

    private final UserJpaRepository userJpaRepository;
    private final CounselInformationJpaRepository counselInformationJpaRepository;
    private final ReserveJpaRepository reserveJpaRepository;

    /**
     * 예약
     */
    @Transactional
    public long reserve(Long userId, List<Long> counselInformationId) {

        User user = userJpaRepository.findOne(userId);

        List<CounselInformation> counselInformationList = new ArrayList<>();

        for (Long id : counselInformationId) {
            CounselInformation counselInformation = counselInformationJpaRepository.findOne(id);
            counselInformationList.add(counselInformation);
        }

        // 예시는 하나만 함(가변인자), JPA활용 - 주문서비스개발 9분
        Reserve reserve = Reserve.createReserve(user, counselInformationList);

        reserveJpaRepository.save(reserve);

        return reserve.getId();

    }

    /**
     * 예약 취소
     */
    @Transactional
    public void cancelReserve(Long reserveId) {
        Reserve reserve = reserveJpaRepository.findOne(reserveId);
        reserve.cancel();
    }

}
