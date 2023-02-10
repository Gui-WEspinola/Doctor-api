package io.github.guiwespinola.doctorsapi.helper;

import com.opencsv.bean.CsvToBeanBuilder;
import io.github.guiwespinola.doctorsapi.entity.dto.DoctorDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Component
public class CSVFileReader {
    public List<DoctorDTO> readCsvFile(MultipartFile file) {
        try (InputStreamReader reader = new InputStreamReader(file.getInputStream())) {
            return new CsvToBeanBuilder<DoctorDTO>(reader)
                    .withType(DoctorDTO.class)
                    .build()
                    .parse();

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}

