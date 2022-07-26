package com.counselink.Counselink.register;


import com.counselink.Counselink.entity.member.Counselor;
import com.counselink.Counselink.entity.member.User;
import com.counselink.Counselink.service.CounselorService;
import com.counselink.Counselink.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/api/register")
@RequiredArgsConstructor
public class RegisterController {

    private final CounselorService counselorService;
    private final UserService userService;

    @PostMapping(path = "/userSignUp")
    public ResponseEntity userSignUp(
            @RequestBody User user
    ) {
        userService.saveUser(user);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    @PostMapping(path = "/counselorSignUp")
    public ResponseEntity<?> counselorSignUp(
            @RequestBody Counselor counselor
        ) {
        counselorService.saveCounselor(counselor);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }
}
