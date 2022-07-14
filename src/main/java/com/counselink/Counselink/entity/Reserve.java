package com.counselink.Counselink.entity;

import com.counselink.Counselink.entity.member.Counselor;
import com.counselink.Counselink.entity.member.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.counselink.Counselink.entity.ReservationStatus.READY;
import static com.counselink.Counselink.entity.ReservationStatus.RESERVE;
import static javax.persistence.FetchType.LAZY;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reserve {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    private LocalDateTime reserveDate;
    private LocalDateTime startCounselTime;
    private LocalDateTime endCounselTime;

    private Integer totalPrice;

    @OneToMany(mappedBy = "reserve", cascade = CascadeType.ALL)
    private final List<CounselInformation> counselInformationList = new ArrayList<>();

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "userreivew_id")
    private UserReview userReview;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "counselorreport_id")
    private CounselorReport counselorReport;

    // Setter
    // builder 패턴으로 바꿔야 한다.
    private void setReserveDate(LocalDateTime reserveDate) {
        this.reserveDate = reserveDate;
    }

    // 상담 시간
    public void setCounselTime() {
        this.startCounselTime = counselInformationList.get(0).getCounselStartTime();
        this.endCounselTime = counselInformationList.get(0).getCounselStartTime().plusMinutes(30L * counselInformationList.size());
    }

    // 예약 가격
    private void setTotalPrice() {
        this.totalPrice = counselInformationList.stream().mapToInt(CounselInformation::getPrice).sum();
    }

    // 연관 관계 메서드
    public void setUser(User user) {
        this.user = user;
        user.getReserveList().add(this);
    }

    public void addCounselInformation(CounselInformation counselInformation) {
        counselInformationList.add(counselInformation);
        counselInformation.setReserve(this);
    }

    public void setUserReview(UserReview userReview) {
        this.userReview = userReview;
        userReview.setReserve(this);
    }

    public void setCounselorReport(CounselorReport counselorReport) {
        this.counselorReport = counselorReport;
        counselorReport.setReserve(this);
    }

    // 비지니스 로직
    // 예약 신청
    // CounselInformation DTO 써야 한다.
    public static Reserve createReserve(User user, List<CounselInformation> counselInformationList) {
        Reserve reserve = new Reserve();

        reserve.setUser(user);
        for (CounselInformation counselInformation : counselInformationList) {
            reserve.addCounselInformation(counselInformation);
            counselInformation.setStatus(RESERVE);
        }
        reserve.setReserveDate(LocalDateTime.now());
        reserve.setCounselTime();
        reserve.setTotalPrice();
        return reserve;
    }

    // 예약 취소
    public void cancel() {
        if(counselInformationList.get(0).getStatus() == RESERVE) {
            throw new IllegalStateException("이미 상담이 끝난 예약입니다.");
        }
        for (CounselInformation counselInformation : counselInformationList) {
            counselInformation.setStatus(READY);
        }
    }

    // 조회 로직

}
