package com.counselink.Counselink.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CounselorReport {

    @Id @GeneratedValue
    @Column(name = "counselorreport_id")
    private Long id;
    private LocalDateTime localDateTime;

    @Column(columnDefinition = "TEXT")
    private String content;

    @OneToOne(mappedBy = "counselorReport")
    private Reserve reserve;

    public void setReserve(Reserve reserve) {
        this.reserve = reserve;
    }
}
