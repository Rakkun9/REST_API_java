package med.hub.api.doctor;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.hub.api.address.Address;


@Table(name = "doctor")
@Entity(name = "Doctor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String document;

    private boolean active;
    @Enumerated(EnumType.STRING)
    private Speciality speciality;
    @Embedded
    private Address address;

    public Doctor(DataRegisterDoctor dataRegisterDoctor) {
        this.active = true;
        this.name = dataRegisterDoctor.name();
        this.email = dataRegisterDoctor.email();
        this.document = dataRegisterDoctor.document();
        this.phone = dataRegisterDoctor.phone();
        this.speciality = dataRegisterDoctor.speciality();
        this.address = new Address(dataRegisterDoctor.address());
    }

    public void updateData(DataUpdateDoctor dataUpdateDoctor) {
        if (dataUpdateDoctor.name() != null){
            this.name = dataUpdateDoctor.name();
        }
        if (dataUpdateDoctor.document() != null){
            this.document = dataUpdateDoctor.document();
        }
        if (dataUpdateDoctor.address() != null){
            this.address = address.updateData(dataUpdateDoctor.address());
        }

    }

    public void disableDoctor() {
        this.active = false;
    }
}
