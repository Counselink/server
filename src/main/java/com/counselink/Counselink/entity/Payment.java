package com.counselink.Counselink.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment {

    @Id
    @GeneratedValue
    @Column(name = "payment_id")
    private Long id;
    private LocalDateTime date;
    private String method;
    private Integer price;
    private String status;
    private LocalDateTime refundDate;

    @OneToOne
    @JoinColumn(name = "counselinformation_id")
    private CounselInformation counselInformation;

}
