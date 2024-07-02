package med.hub.api.doctor;

import med.hub.api.address.DataAddress;

public record DataResponseDoctor(
        Long id,
        String name,
        String email,
        String phone,
        String document,
        DataAddress address) {
}

