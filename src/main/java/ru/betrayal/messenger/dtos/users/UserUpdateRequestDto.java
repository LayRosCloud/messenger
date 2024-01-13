package ru.betrayal.messenger.dtos.users;

import lombok.*;

@Data
public class UserUpdateRequestDto {
    private Long id;
    private String email;
    private String password;
    private String status;
}
