package com.counselink.Counselink.api.counselor;

import com.counselink.Counselink.repository.counselor.CounselorRepository;
import com.counselink.Counselink.repository.counselor.HomePageCounselorDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CounselorApiController {

    private final CounselorRepository counselorRepository;

    @GetMapping("/api/homepage/counselor")
    public Result<List<HomePageCounselorDto>> GetHomepageUserReview() {
        List<HomePageCounselorDto> counselorsWithImageUrl = counselorRepository.findCounselorsWithImageUrl();

        return new Result<>(counselorsWithImageUrl);
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }
}
