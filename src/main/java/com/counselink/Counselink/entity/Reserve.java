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
    
    @OneToMany(mappedBy = "reserve")
    private final List<CounselInformation> counselInformationList = new ArrayList<>();

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "clientreivew_id")
    private ClientReview clientReview;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "counselorreport_id")
    private CounselorReport counselorReport;

    // Setter
    // builder 패턴으로 바꿔야 한다.
    public void setReserveDate(LocalDateTime reserveDate) {
        this.reserveDate = reserveDate;
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

    public void setCounselorReport(CounselorReport counselorReport) {
        this.counselorReport = counselorReport;
        counselorReport.setReserve(this);
    }

    // 비지니스 로직
    // 예약 신청
    // CounselInformation DTO 써야 한다.
    public static Reserve createReserve(User user, Counselor counselor, List<CounselInformation> counselInformation) {
        Reserve reserve = new Reserve();

        reserve.setUser(user);
        for (CounselInformation information : counselInformation) {
            reserve.addCounselInformation(information);
            information.setStatus(RESERVE);
        }
        reserve.setReserveDate(LocalDateTime.now());

        return reserve;
    }

    // 예약 취소
    public void cancel() {
        if(counselInformationList.get(0).getStatus() == RESERVE) {
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for (CounselInformation counselInformation : counselInformationList) {
            counselInformation.setStatus(READY);
        }

    }

    // 조회 로직
    // 예약 가격
    public int getTotalPrice() {
        return counselInformationList.stream().mapToInt(CounselInformation::getPrice).sum();
    }
}
