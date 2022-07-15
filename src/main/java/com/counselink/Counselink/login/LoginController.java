package com.counselink.Counselink.login;

import com.counselink.Counselink.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Response;

@Controller
@RequestMapping(value = "/api/login")
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/signin")
    public ResponseEntity<?> signin(
        @RequestBody String ID,
        @RequestBody String passwd,
        HttpServletRequest request
    ) {
        if (userService.signinValidation(ID, passwd)) {
            return ResponseEntity.ok().body(null);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

}
