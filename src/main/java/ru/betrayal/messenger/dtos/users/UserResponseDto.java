package ru.betrayal.messenger.dtos.users;

import lombok.*;

import java.util.Date;

@Data
public class UserResponseDto {
    private Long id;
    private String email;
    private String image;
    private String status;
    private Date dateRegistration;
}
