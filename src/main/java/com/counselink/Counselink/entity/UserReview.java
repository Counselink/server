package com.counselink.Counselink.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserReview {

    @Id @GeneratedValue
    @Column(name = "userreivew_id")
    private Long id;
    private LocalDateTime localDateTime;

    @Column(columnDefinition = "TEXT")
    private String content;
    private int stars;

    @OneToOne(mappedBy = "userReview")
    private Reserve reserve;

    public void setReserve(Reserve reserve) {
        this.reserve = reserve;
    }
}
