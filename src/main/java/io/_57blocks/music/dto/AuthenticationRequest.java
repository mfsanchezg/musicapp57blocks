package io._57blocks.music.dto;

import io._57blocks.music.validations.Password;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

    @Email(message = "The email is not valid.")
    private String email;

    @Password
    private String password;

}
