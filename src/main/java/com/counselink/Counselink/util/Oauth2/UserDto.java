package com.counselink.Counselink.util.Oauth2;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserDto {
    private String email;
    private String name;
    private String picture;

    @Builder
    public UserDto(String email, String name, String picture) {
        this.email = email;
        this.name = name;
        this.picture = picture;
    }
}