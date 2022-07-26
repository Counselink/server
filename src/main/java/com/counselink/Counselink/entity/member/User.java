package com.counselink.Counselink.entity.member;

import com.counselink.Counselink.entity.Address;
import com.counselink.Counselink.entity.Reserve;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
// @Entity는 기본 생성자가 있어야 한다.
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "userName", "phoneNumber"})
@Table(name = "users")
public class User {

    @Id @GeneratedValue()
    @Column(name = "user_id")
    private Long id;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "email")
    private String email;
    @Column(name = "login_id")
    private String loginId;
    @Column(name = "login_password")
    private String loginPassword;
    @Column(name = "register_date")
    private LocalDateTime registerDate;

    @Embedded
    @Column(name = "address")
    private Address address;

    // mappedBy로 데이터를 변경해도 변경되지 않는다.
    @OneToMany(mappedBy = "user")
    @Column(name = "reserver_list")
    private final List<Reserve> reserveList = new ArrayList<>();

    @Builder
    public User(String userName, String phoneNumber, String email, String loginId, String loginPassword, Address address) {
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.loginId = loginId;
        this.loginPassword = loginPassword;
        this.registerDate = LocalDateTime.now();
        this.address = address;
    }

    public void changeUserName(String userName) {
        this.userName= userName;
    }

}
