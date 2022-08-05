package com.counselink.Counselink.api.scheduling;

import com.counselink.Counselink.entity.CounselInformation;
import com.counselink.Counselink.service.ReserveService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Parameter;
import org.hibernate.validator.constraints.ParameterScriptAssert;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping(value = "/api/scheduling")
@RequiredArgsConstructor
@Api(tags = {"일정"})
public class SchedulingController {

    private final ReserveService reserveService;

    @GetMapping(path = "/calendarStatus/{year}/{month}")
    public ResponseEntity<?> calendarStatus(
            @PathVariable Integer year,
            @PathVariable Integer month
    ) {
        ArrayList<Map<String, String>> res = reserveService.getCalendarStatus(year, month);
        return ResponseEntity.ok().body(res.toString());
    }
}
