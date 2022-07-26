package com.counselink.Counselink.login;

import com.counselink.Counselink.common.exception.InvalidLoginException;
import com.counselink.Counselink.entity.member.User;
import com.counselink.Counselink.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/api/login")
public class LoginController {

    private final UserService userService;

    // 회원 가입.
    @PostMapping(path = "/signup")
    public ResponseEntity<?> signup(
        @RequestBody User user,
        HttpServletRequest request
    ) {
        if (userService.isSignupValid(user.getLoginId(), user.getLoginPassword())) {
            userService.saveUser(user);
            return ResponseEntity.ok().body(user.toString());
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // 로그인
    @PostMapping(path = "/login")
    public ResponseEntity<?> login(
        @RequestBody User user
    ) throws InvalidLoginException {
        userService.isLoginValid(user);
        return ResponseEntity.ok().body("login is done.");
    }
}
