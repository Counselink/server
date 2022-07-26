package com.counselink.Counselink.login;

import com.counselink.Counselink.entity.member.User;
import com.counselink.Counselink.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@Controller
@RequestMapping(value = "/api/login")
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    // 로그인
    @PostMapping(path = "/login")
    public ResponseEntity<?> login(
        @RequestBody User user
    ) {
        userService.isLoginValid(user);
        return ResponseEntity.ok().body(null);
    }
}
