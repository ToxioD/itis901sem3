package ru.itis.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@AllArgsConstructor
@Builder
public class UserForm {
    private String email;
    private String firstName;
    private String lastName;
    private String password;
}
