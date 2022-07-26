package com.counselink.Counselink.repository.counselor;

import lombok.Data;

@Data
public class HomePageCounselorDto {
    private String url;
    private String career;

    public HomePageCounselorDto(String url, String career) {
        this.url = url;
        this.career = career;
    }
}
