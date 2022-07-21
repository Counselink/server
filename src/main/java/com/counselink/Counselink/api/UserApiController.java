package com.counselink.Counselink.api;

import com.counselink.Counselink.entity.member.User;
import com.counselink.Counselink.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    @PutMapping("/api/user/{id}")
    public UpdateUserNameResponseDto updateMember(
            @PathVariable("id") Long id,
            @RequestBody @Valid UpdateUserNameRequestDto request) {

        userService.update(id, request.getName());
        User findUser = userService.findById(id);

        return new UpdateUserNameResponseDto(findUser.getId(), findUser.getUserName());
    }

    @Data
    @AllArgsConstructor
    private static class UpdateUserNameRequestDto {
        private String name;
    }

    @Data
    @AllArgsConstructor
    private static class UpdateUserNameResponseDto {
        private Long id;
        private String name;
    }


}
