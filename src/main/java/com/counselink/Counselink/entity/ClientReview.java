package com.counselink.Counselink.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClientReview {

    @Id @GeneratedValue
    @Column(name = "clientreivew_id")
    private Long id;
    private LocalDateTime localDateTime;

    @Column(columnDefinition = "TEXT")
    private String content;
    private int stars;

    @OneToOne(mappedBy = "clientReview")
    private Reserve reserve;
}
