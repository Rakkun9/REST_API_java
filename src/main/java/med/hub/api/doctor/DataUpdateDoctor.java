package med.hub.api.doctor;

import jakarta.validation.constraints.NotNull;
import med.hub.api.address.DataAddress;

public record DataUpdateDoctor(@NotNull Long id, String name, String document, DataAddress address) {

}
