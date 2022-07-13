package com.counselink.Counselink.entity.member;

import com.counselink.Counselink.entity.CounselInformation;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "counselorName", "phoneNumber"})
public class Counselor {

    @Id @GeneratedValue
    @Column(name = "counselor_id")
    private Long id;
    private String counselorName;
    private String phoneNumber;
    private String email;
    private String loginId;
    private String loginPassword;
    private Date registerDate;

    @Column(columnDefinition = "TEXT")
    private String career;
    private String profileImageUrl;

    @OneToMany(mappedBy = "counselor")
    private final List<CounselInformation> counselInformationList  = new ArrayList<>();

    public void changeCounselorName(String counselorName) {
        this.counselorName = counselorName;
    }
}
