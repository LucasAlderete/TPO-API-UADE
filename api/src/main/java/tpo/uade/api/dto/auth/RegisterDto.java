package tpo.uade.api.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tpo.uade.api.model.RoleEnum;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto implements Serializable {

        @NotBlank(message = "{register-dto.username-not-blank}")
        private String username;

        @NotBlank(message = "{register-dto.email-not-blank}")
        private String email;

        @NotBlank(message = "{register-dto.password-not-blank}")
        private String password;

        @NotNull(message = "{register-dto.birthday-not-null}")
        private LocalDate birthday;

        @NotBlank(message = "{register-dto.name-not-blank}")
        private String name;

        @NotBlank(message = "{register-dto.surname-not-blank}")
        private String surname;

        private RoleEnum role;

        @Override
        public String toString() {
                return "RegisterDto{" +
                        "username='" + username + '\'' +
                        ", email='" + email + '\'' +
                        ", password='" + password + '\'' +
                        ", birthday=" + birthday +
                        ", name='" + name + '\'' +
                        ", surname='" + surname + '\'' +
                        ", role=" + role +
                        '}';
        }
}