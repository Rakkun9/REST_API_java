package med.hub.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.hub.api.doctor.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    @PostMapping
    public ResponseEntity registerDoctor(@RequestBody @Valid DataRegisterDoctor dataRegisterDoctor) {
        Doctor doctor = doctorRepository.save(new Doctor(dataRegisterDoctor));
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public Page<DataListDoctor> listDoctor(@PageableDefault(size = 2) Pageable pageable) {
        //pageable is a parameter that allows you to navigate through the pages
        return doctorRepository.findByActiveTrue(pageable).map(DataListDoctor::new);
    }
    @PutMapping
    @Transactional
    public ResponseEntity updateDoctor(@RequestBody @Valid DataUpdateDoctor dataUpdateDoctor){
        Doctor doctor = doctorRepository.getReferenceById(dataUpdateDoctor.id());
        doctor.updateData(dataUpdateDoctor);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @Transactional

    public ResponseEntity deleteDoctor(@PathVariable Long id){
        Doctor doctor = doctorRepository.getReferenceById(id);
        doctor.disableDoctor();
        return ResponseEntity.noContent().build();
        //The responseEntity is a class that allows you to return a status code
    }

//    Delete in the database
//    public void deleteDoctor(@PathVariable Long id){
//        Doctor doctor = doctorRepository.getReferenceById(id);
//        doctorRepository.delete(doctor);
//    }
}
