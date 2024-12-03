package tpo.uade.api.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;

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
public class UserResponseDto implements Serializable {

    private String username;

    private String email;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate birthday;

    private String name;

    private String surname;

    private String role;
}