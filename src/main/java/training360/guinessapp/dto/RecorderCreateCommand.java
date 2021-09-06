package training360.guinessapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecorderCreateCommand {
    @NotBlank(message = "must not be blank")
    @NotNull
    private String name;

    @Past(message = "must be in the past")
    private LocalDate dateOfBirth;
}
