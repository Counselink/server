package com.counselink.Counselink.entity;

import com.counselink.Counselink.entity.member.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import static com.counselink.Counselink.entity.ReservationStatus.RESERVE;

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
    private Integer stars;

    @OneToOne(mappedBy = "userReview")
    private Reserve reserve;

    //== 연관관계 메서드 ==//
    public void setReserve(Reserve reserve) {
        this.reserve = reserve;
        reserve.setUserReview(this);
    }

    public void writeContent(String content) {
        this.content = content;
    }

    public void giveStars(Integer stars) {
        this.stars = stars;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    //== 생성 메서드 ==//
    public static UserReview createUserReview(String content, Integer stars, Reserve reserve) {
        UserReview userReview = new UserReview();

        userReview.writeContent(content);
        userReview.giveStars(stars);
        userReview.setLocalDateTime(LocalDateTime.now());
        userReview.setReserve(reserve);

        return userReview;
    }
}
