package com.counselink.Counselink.repository.user_review;

import com.counselink.Counselink.entity.UserReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserReviewRepository extends JpaRepository<UserReview, Long> {

    @Query("select new com.counselink.Counselink.repository.user_review.HomePageUserReviewDto(u.age, u.userName, ur.content, ur.stars) from UserReview ur join ur.reserve r join r.user u where ur.stars >= 4")
    List<HomePageUserReviewDto> findHomepageUserReview();
}
