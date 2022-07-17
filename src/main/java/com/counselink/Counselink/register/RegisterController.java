package com.counselink.Counselink.register;


import com.counselink.Counselink.entity.member.Counselor;
import com.counselink.Counselink.entity.member.User;
import com.counselink.Counselink.service.CounselorService;
import com.counselink.Counselink.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/api/register")
@RequiredArgsConstructor
public class RegisterController {

    private final CounselorService counselorService;
    private final UserService userService;

    @PostMapping(path = "/userSignUp")
    public ResponseEntity<?> userSignUp(
            @RequestBody User user,
            HttpServletRequest request) {

        System.out.println(user.toString());

        User tmp = userService.saveUser(user);

        if (tmp != null) {  // userService 는 저장한 내용을 다시 return 함. 따라서 null이 아니면 저장 성공
            return ResponseEntity.ok().body(tmp.toString());
        } else {            // 그렇지 않으면 저장 실패
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping(path = "/counselorSignUp")
    public ResponseEntity<?> counselorSignUp(
            @RequestBody Counselor counselor,
            HttpServletRequest request
        ) {
        // for debug
        Counselor tmp = counselorService.saveCounselor(counselor);

        if (tmp != null) {  // counselorService 는 저장한 내용을 다시 return 함. 따라서 null이 아니면 저장 성공
            return ResponseEntity.ok().body(tmp.toString());
        } else {            // 그렇지 않으면 저장 실패
            return ResponseEntity.badRequest().body(null);
        }
    }
}
