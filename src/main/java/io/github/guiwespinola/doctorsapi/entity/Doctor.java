package io.github.guiwespinola.doctorsapi.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String plantao;

    private String triagem;

    private String visita;

    private String cirurgia;

    private Integer totalPlantoes;

    public void calcularTotalPlantoes() {
        this.totalPlantoes = Math.toIntExact(Math.round(Double.parseDouble(plantao) + 0.5 * Double.parseDouble(visita) + 0.5 * Double.parseDouble(triagem)));
    }
}
