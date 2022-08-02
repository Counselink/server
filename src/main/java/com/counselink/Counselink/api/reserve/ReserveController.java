package com.counselink.Counselink.api.reserve;

import com.counselink.Counselink.api.user.UpdateUserController;
import com.counselink.Counselink.entity.CounselInformation;
import com.counselink.Counselink.service.CounselInformationService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class ReserveController {

    private final CounselInformationService counselInformationService;

    @GetMapping("/api/reserve/check")
    public void InquiryCounsel(@RequestBody @Valid checkTimeRequestDto request) {

        List<CounselInformation> counselInformationList = counselInformationService.checkTime(request.category, request.counselDate, request.startTime, request.endTime);

    }

    @Data
    static class checkTimeRequestDto {
        private String category;
        private LocalDateTime counselDate;
        private LocalDateTime startTime;
        private LocalDateTime endTime;
    }

}
