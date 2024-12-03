package tpo.uade.api.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto implements Serializable {

    @NotBlank(message = "{user-dto.username-not-blank}")
    private String username;

    @NotBlank(message = "{user-dto.email-not-blank}")
    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "{user-dto.birthday-not-null}")
    private LocalDate birthday;

    @NotBlank(message = "{user-dto.name-not-blank}")
    private String name;

    @NotBlank(message = "{user-dto.surname-not-blank}")
    private String surname;

}