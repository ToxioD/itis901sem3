package ru.itis.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@Builder
public class UserForm {
    @NotEmpty(message = "Email should not be empty")
    @Email
    private String email;

    @NotEmpty(message = "First name should not be empty")
    @Length(min = 2,
            max = 20,
            message = "First name must be between {min} and {max} characters long")
    private String firstName;

    @NotEmpty(message = "Last name should not be empty")
    @Length(min = 3,
            max = 20,
            message = "Last name must be between {min} and {max} characters long")
    private String lastName;

    @NotEmpty(message = "Password should not be empty")
    @Length(min = 8,
            max = 20,
            message = "Password must be between {min} and {max} characters long")
    private String password;
}
