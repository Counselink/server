package com.counselink.Counselink.entity;

import com.counselink.Counselink.entity.member.Counselor;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

import static com.counselink.Counselink.entity.ReservationStatus.READY;
import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CounselInformation {

    @Id
    @GeneratedValue
    @Column(name = "counselinformation_id")
    private Long id;
    private String category;
    @Enumerated(EnumType.STRING)
    private ReservationStatus status;
    private LocalDateTime counselDate;
    private LocalDateTime counselStartTime;
    private Integer price;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "counselor_id")
    private Counselor counselor;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "reserve_id")
    private Reserve reserve;

    // Setter
    private void setCategory(String category) {
        this.category = category;
    }

    private void setCounselDate(LocalDateTime counselDate) {
        this.counselDate = counselDate;
    }

    private void setCounselStartTime(LocalDateTime counselStartTime) {
        this.counselStartTime = counselStartTime;
    }

    private void setPrice(Integer price) {
        this.price = price;
    }

    public void setReserve(Reserve reserve) {
        this.reserve = reserve;
    }

    public void setCounselor(Counselor counselor) {
        this.counselor = counselor;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    // 생성 메서드
    public static CounselInformation createCounselInformation(String category, LocalDateTime counselDate, LocalDateTime counselStartTime, Integer price, Counselor counselor) {
        CounselInformation counselInformation = new CounselInformation();
        counselInformation.setCategory(category);
        counselInformation.setCounselDate(counselDate);
        counselInformation.setCounselStartTime(counselStartTime);
        counselInformation.setPrice(price);
        counselInformation.setCounselor(counselor);
        counselInformation.setStatus(READY);
        return counselInformation;
    }
}
