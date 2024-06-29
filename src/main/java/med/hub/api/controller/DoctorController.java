package med.hub.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.hub.api.doctor.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    @PostMapping
    public void registerDoctor(@RequestBody @Valid DataRegisterDoctor dataRegisterDoctor) {
        doctorRepository.save(new Doctor(dataRegisterDoctor));
    }

    @GetMapping
    public Page<DataListDoctor> listDoctor(@PageableDefault(size = 2) Pageable pageable) {
        //pageable is a parameter that allows you to navigate through the pages
        return doctorRepository.findByActiveTrue(pageable).map(DataListDoctor::new);
    }
    @PutMapping
    @Transactional
    public void updateDoctor(@RequestBody @Valid DataUpdateDoctor dataUpdateDoctor){
        Doctor doctor = doctorRepository.getReferenceById(dataUpdateDoctor.id());
        doctor.updateData(dataUpdateDoctor);
    }

    @DeleteMapping("/{id}")
    @Transactional

    public void deleteDoctor(@PathVariable Long id){
        Doctor doctor = doctorRepository.getReferenceById(id);
        doctor.disableDoctor();
    }

//    Delete in the database
//    public void deleteDoctor(@PathVariable Long id){
//        Doctor doctor = doctorRepository.getReferenceById(id);
//        doctorRepository.delete(doctor);
//    }
}
