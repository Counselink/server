package com.counselink.Counselink.entity.member;

import com.counselink.Counselink.entity.Address;
import com.counselink.Counselink.entity.CounselInformation;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "counselorName", "phoneNumber"})
public class Counselor {

    @Id
    @GeneratedValue
    @Column(name = "counselor_id")
    private Long id;
    private String counselorName;
    private Integer age;
    private String phoneNumber;
    private String email;
    private String loginId;
    private String loginPassword;
    private LocalDateTime registerDate;
    @Embedded
    private Address address;

    @Column(columnDefinition = "TEXT")
    private String career;
    private String profileImageUrl;

    @OneToMany(mappedBy = "counselor")
    private final List<CounselInformation> counselInformationList = new ArrayList<>();

    public void changeCounselorName(String counselorName) {
        this.counselorName = counselorName;
    }

    @Builder
    public Counselor(String counselorName, Integer age, String phoneNumber, String email, String loginId, String loginPassword, Address address, String career, String profileImageUrl) {
        this.counselorName = counselorName;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.loginId = loginId;
        this.loginPassword = loginPassword;
        this.registerDate = LocalDateTime.now();
        this.address = address;
        this.career = career;
        this.profileImageUrl = profileImageUrl;
    }
}
