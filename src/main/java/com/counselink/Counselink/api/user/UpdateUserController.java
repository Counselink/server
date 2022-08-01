package com.counselink.Counselink.api.user;

import com.counselink.Counselink.entity.member.User;
import com.counselink.Counselink.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UpdateUserController {

    private final UserService userService;

    @PutMapping("/api/update/user/name/{id}")
    public UpdateUserNameResponseDto updateMember(
            @PathVariable("id") Long id,
            @RequestBody @Valid UpdateUserNameRequestDto request) {

        userService.update(id, request.getUserName());
        User findUser = userService.findById(id);

        return new UpdateUserNameResponseDto(findUser.getId(), findUser.getUserName());
    }

    @PutMapping("/api/user/info")
    public ResponseEntity updateUserInfo(
            @Param("id") Long id,
            @RequestBody UpdateUserInfoRequestDto request) {

        userService.updateUserInfo(id,request.getEmail(), request.getPasswords());

        return ResponseEntity.ok(HttpStatus.OK);
    }



    @Data
    static class UpdateUserNameRequestDto {
        @NotEmpty
        private String userName;
    }

    @Data
    static class UpdateUserInfoRequestDto {
        @NotEmpty
        private String email;
        private String passwords;
    }
    @Data
    @AllArgsConstructor
    static class UpdateUserNameResponseDto {
        private Long id;
        private String userName;
    }


}
