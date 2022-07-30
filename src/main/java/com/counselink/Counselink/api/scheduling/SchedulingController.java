package com.counselink.Counselink.api.scheduling;

import com.counselink.Counselink.entity.CounselInformation;
import com.counselink.Counselink.service.ReserveService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping(value = "/api/scheduling")
@RequiredArgsConstructor
@Api(tags = {"일정"})
public class SchedulingController {

    private final ReserveService reserveService;

    @PostMapping(path = "/register")
    public ResponseEntity<String> register(@RequestBody CounselInformation ci) {
        reserveService.saveCounselInfo(ci);

        return ResponseEntity.ok().body(ci.toString());
    }
}
