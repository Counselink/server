package com.counselink.Counselink.repository.user_review;

import lombok.Data;

@Data
public class HomePageUserReviewDto {
    private Integer age;
    private final String userName;
    private final String content;
    private final Integer stars;

    public HomePageUserReviewDto(Integer age, String userName, String content, Integer stars) {

        char c = userName.charAt(0);
        int length = userName.length() - 1;

        this.age = age;
        this.userName = c + "*".repeat(length);
        this.content = content;
        this.stars = stars;
    }
}
