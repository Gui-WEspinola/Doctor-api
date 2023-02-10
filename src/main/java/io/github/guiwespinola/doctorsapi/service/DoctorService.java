package io.github.guiwespinola.doctorsapi.service;

import io.github.guiwespinola.doctorsapi.entity.Doctor;
import io.github.guiwespinola.doctorsapi.entity.dto.DoctorDTO;
import io.github.guiwespinola.doctorsapi.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;

    private final ModelMapper mapper;

    public List<Doctor> saveAll(List<DoctorDTO> doctorsDTO) {
        List<Doctor> doctors = doctorsDTO.stream().map(doctorDTO -> mapper.map(doctorDTO, Doctor.class)).toList();
        return doctorRepository.saveAll(doctors);
    }

    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }
}
