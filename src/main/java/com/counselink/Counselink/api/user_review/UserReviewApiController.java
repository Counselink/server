package com.counselink.Counselink.api.user_review;

import com.counselink.Counselink.repository.user_review.HomePageUserReviewDto;
import com.counselink.Counselink.repository.user_review.UserReviewRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserReviewApiController {

    private final UserReviewRepository userReviewRepository;

    @GetMapping("/api/homepage/review")
    public Result<List<HomePageUserReviewDto>> GetHomepageUserReview() {
        List<HomePageUserReviewDto> userReviewOnHomepage = userReviewRepository.findHomepageUserReview();
        
        return new Result<>(userReviewOnHomepage);
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }
}
