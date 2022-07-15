package com.counselink.Counselink.repository.spring_data_jpa;

import com.counselink.Counselink.entity.UserReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserReviewRepository extends JpaRepository<UserReview, Long> {
}
