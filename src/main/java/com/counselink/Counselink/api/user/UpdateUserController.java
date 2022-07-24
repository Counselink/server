package com.counselink.Counselink.api.user;

import com.counselink.Counselink.entity.member.User;
import com.counselink.Counselink.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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

    @Data
    static class UpdateUserNameRequestDto {
        @NotEmpty
        private String userName;
    }

    @Data
    @AllArgsConstructor
    static class UpdateUserNameResponseDto {
        private Long id;
        private String userName;
    }


}
