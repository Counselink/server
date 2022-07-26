package com.counselink.Counselink.login;

import com.counselink.Counselink.entity.member.Counselor;
import com.counselink.Counselink.entity.member.User;
import com.counselink.Counselink.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/api/login")
public class LoginController {

    private final UserService userService;
    private final CounselorService counselorService;


    // User Login
    @PostMapping(path = "/userLogin")
    public ResponseEntity<?> userLogin(
            @RequestBody User user

    ) {
        userService.isLoginValid(user);
        return ResponseEntity.ok().body(null);
    }

    // Counselor Login
    @PostMapping(path = "/counselorLogin")
    public ResponseEntity<?> counselorLogin(
            @RequestBody Counselor counselor
    ) throws Exception {
        counselorService.isLoginValid(counselor);
        return ResponseEntity.ok().body(null);

    }
}
