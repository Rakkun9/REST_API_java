package med.hub.api.doctor;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.hub.api.address.DataAddress;

public record DataRegisterDoctor(

        @NotBlank
        String name,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String phone,

        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String document,

        @NotNull
        Speciality speciality,

        @Valid
        @NotNull
        DataAddress address
) {

}
