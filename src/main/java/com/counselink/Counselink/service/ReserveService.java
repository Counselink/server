package com.counselink.Counselink.service;

import com.counselink.Counselink.entity.CounselInformation;
import com.counselink.Counselink.entity.Reserve;
import com.counselink.Counselink.entity.member.Counselor;
import com.counselink.Counselink.entity.member.User;
import com.counselink.Counselink.repository.ClientJpaRepository;
import com.counselink.Counselink.repository.CounselInformationJpaRepository;
import com.counselink.Counselink.repository.CounselorJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReserveService {

    private final ClientJpaRepository clientJpaRepository;
    private final CounselorJpaRepository counselorJpaRepository;
    private final CounselInformationJpaRepository counselInformationJpaRepository;

    @Transactional
    public long Reserve(Long clientId, Long counselorId, List<Long> counselInformationId) {

        User user = clientJpaRepository.findOne(clientId);
        Counselor counselor = counselorJpaRepository.findOne(counselorId);

        List<CounselInformation> counselInformationList = new ArrayList<>();

        for (Long id : counselInformationId) {
            CounselInformation counselInformation = counselInformationJpaRepository.findOne(id);
            counselInformationList.add(counselInformation);
        }

        Reserve reserve = Reserve.createReserve(user, counselor, counselInformationList);

        return reserve.getId();

    }

}
