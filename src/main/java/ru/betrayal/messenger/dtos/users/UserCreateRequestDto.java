package ru.betrayal.messenger.dtos.users;

import lombok.*;

@Data
public class UserCreateRequestDto {
    private String email;
    private String password;
}
