package br.com.fiap;

import jakarta.json.bind.annotation.JsonbDateFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Carro {
    private String numChassi;
    private String placaCarro;
    private String modeloCarro;
    private String montadoraCarro;

    @JsonbDateFormat("yyyy-MM-dd")
    public LocalDate anoCarro;
}