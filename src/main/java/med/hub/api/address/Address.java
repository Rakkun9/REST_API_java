package med.hub.api.address;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Address {
    private String street;
    private String district;
    private String city;
    private String number;
    private String complement;

    public Address(DataAddress address) {
        this.street = address.street();
        this.city = address.city();
        this.complement = address.complement();
        this.district = address.district();
        this.number = address.number();
    }

    public Address updateData(DataAddress address) {
        this.street = address.street();
        this.city = address.city();
        this.complement = address.complement();
        this.district = address.district();
        this.number = address.number();
        return this;
    }
}

