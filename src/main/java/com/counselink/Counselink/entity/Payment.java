package com.counselink.Counselink.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@onstructor(access = AccessLevel.PROTECTED)
public class Payment {ÅÍÎ
    @Id
    @GeneratedValue
    @Column(name = "payment_id")
    private Long id;
    private LocalDateTime date;
    private String method;
    private Integer price;
    private String status;
    private LocalDateTime refundDate;

    @OneToMany(mappedBy = "payment")
    private List<CounselInformation> counselInformationList = new ArrayList<>();


}
