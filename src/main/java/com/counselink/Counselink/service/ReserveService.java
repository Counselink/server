package com.counselink.Counselink.service;

import com.counselink.Counselink.entity.CounselInformation;
import com.counselink.Counselink.entity.Reserve;
import com.counselink.Counselink.entity.member.User;
import com.counselink.Counselink.repository.CounselInformationRepository;
import com.counselink.Counselink.repository.ReserveRepository;
import com.counselink.Counselink.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.asm.Advice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.*;

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

    public ArrayList<Map<String, String>> getCalendarStatus(Integer year, Integer month) {
        List<Optional<Reserve>> reserves =  reserveRepository.findReservesOnCalendarByYearAndMonth(year, month);

        var res = new ArrayList<Map<String, String>>();
        for(Optional<Reserve> r: reserves) {
            var reserveDate = r.get().getReserveDate();
            var endCounselTime = r.get().getEndCounselTime();
            var userReview = r.get().getUserReview();

            // 현재 시간이 endCounselTime 보다 뒤라면 상담 시간 초과
            Boolean isTimeOver = LocalDateTime.of(2022, 8, 2, 0, 0).isAfter(endCounselTime);

            // userReview 가 null 이 아니라면 review 가 작성된 것
            Boolean isReviewd = userReview != null;

            // 상담 진행 여부는 이후에 api 로 더 추가해서 고려할 부분
            // Boolean isCounsel = ...

            // gray : 상담 시간 초과 O, 상담 진행 X, 리뷰 X
            // red  : 상담 시간 초과 O, 상담 진행 O, 리뷰 X
            // blue : 상담 시간 초과 O, 상담 진행 O, 리뷰 O
            // green: 상담 시간 초과 X, 상담 진행 O, 리뷰 O
            String color = "";
            if (isTimeOver && !isReviewd) {
                color = "grey"; // 두 경우가 중복됨
                color = "red";
            } else if (isTimeOver && isReviewd) {
                color = "blue";
            } else if (!isTimeOver && isReviewd) {
                color = "green";
            } else { // default
                color = "black";
            }

            var datetimeStatusMap = new HashMap<String, String>();
            datetimeStatusMap.put("date", reserveDate.toString());
            datetimeStatusMap.put("status", color);

            res.add(datetimeStatusMap);
        }

        return res;
    }
}


