package com.counselink.Counselink.api.register;


import com.counselink.Counselink.entity.Address;
import com.counselink.Counselink.entity.member.Counselor;
import com.counselink.Counselink.entity.member.User;
import com.counselink.Counselink.service.CounselorService;
import com.counselink.Counselink.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Controller
@RequestMapping(value = "/api/register")
@RequiredArgsConstructor
public class RegisterController {

    private final CounselorService counselorService;
    private final UserService userService;

    @PostMapping(path = "/userSignUp")
    public ResponseEntity userSignUp(@Valid @RequestBody RegisterUserDto registerUserDto) {

        User user = User.builder()
                .userName(registerUserDto.getUserName())
                .age(registerUserDto.getAge())
                .phoneNumber(registerUserDto.getPhoneNumber())
                .email(registerUserDto.getEmail())
                .loginId(registerUserDto.getLoginId())
                .loginPassword(registerUserDto.getLoginPassword())
                .address(Address.builder().build())
                .build();


        userService.saveUser(user);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    @PostMapping(path = "/counselorSignUp")
    public ResponseEntity<?> counselorSignUp(@Valid @RequestBody RegisterCounselorDto registerCounselorDto) {

        Counselor counselor = Counselor.builder()
                .counselorName(registerCounselorDto.getCounselorName())
                .age(registerCounselorDto.getAge())
                .phoneNumber(registerCounselorDto.getPhoneNumber())
                .email(registerCounselorDto.getEmail())
                .loginId(registerCounselorDto.getLoginId())
                .loginPassword(registerCounselorDto.getLoginPassword())
                .address(Address.builder().build())
                .career(registerCounselorDto.getCareer())
                .profileImageUrl(registerCounselorDto.getProfileImageUrl())
                .build();

        counselorService.saveCounselor(counselor);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    @Data
    static class RegisterUserDto {
        @NotEmpty
        private String userName;
        @NotEmpty
        private Integer age;
        @NotEmpty
        private String phoneNumber;
        @NotEmpty
        private String email;
        @NotEmpty
        private String loginId;
        @NotEmpty
        private String loginPassword;
        @NotEmpty
        private Address address;
    }

    @Data
    static class RegisterCounselorDto {
        private String counselorName;
        private Integer age;
        private String phoneNumber;
        private String email;
        private String loginId;
        private String loginPassword;
        private String career;
        private String profileImageUrl;
    }

}
