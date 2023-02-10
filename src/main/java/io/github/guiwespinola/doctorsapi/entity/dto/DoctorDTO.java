package io.github.guiwespinola.doctorsapi.entity.dto;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDTO {

    @CsvBindByName(column = "NOME")
    private String nome;

    @CsvBindByName(column = "PLANTAO")
    private Integer plantao;

    @CsvBindByName(column = "TRIAGEM")
    private Integer triagem;

    @CsvBindByName(column = "VISITA")
    private Integer visita;

    @CsvBindByName(column = "CIRURGIA")
    private Integer cirurgia;
}
