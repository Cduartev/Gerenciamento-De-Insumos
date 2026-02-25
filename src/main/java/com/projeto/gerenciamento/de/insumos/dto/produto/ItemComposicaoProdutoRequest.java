package com.projeto.gerenciamento.de.insumos.dto.produto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ItemComposicaoProdutoRequest(

        @NotNull(message = "O id da materia-prima é obrigatorio.")
        Long materiaPrimaId,
        @NotNull(message = "A quantidade necessaria é obrigatoria.")
        @DecimalMin(value = "0.0001", inclusive = true, message = "A quantidade necessaria deve ser maior que zero.")
        BigDecimal quantidadeNecessaria
) {
}
