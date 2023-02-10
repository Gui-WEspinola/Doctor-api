package io.github.guiwespinola.doctorsapi.controller;

import io.github.guiwespinola.doctorsapi.entity.Doctor;
import io.github.guiwespinola.doctorsapi.entity.dto.DoctorDTO;
import io.github.guiwespinola.doctorsapi.helper.CSVFileReader;
import io.github.guiwespinola.doctorsapi.helper.PDFWriter;
import io.github.guiwespinola.doctorsapi.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/doctors/")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    private final CSVFileReader csvFileReader;

    private final PDFWriter writer;

    @PostMapping("/upload/monthly-activities")
    public ResponseEntity<List<Doctor>> saveMonthlyActivities(MultipartFile file) {
        List<DoctorDTO> doctorDTOList = csvFileReader.readCsvFile(file);

        return ResponseEntity.status(HttpStatus.CREATED).body(doctorService.saveAll(doctorDTOList));
    }

    @GetMapping("/generate-pdf")
    public ResponseEntity<?> generatePDF() {
        List<Doctor> doctorList = doctorService.findAll();

        writer.createLocalPdf(doctorList, "doctors.pdf");

        return ResponseEntity.ok().build();
    }

}
