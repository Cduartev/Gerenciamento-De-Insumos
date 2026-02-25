package com.project.inventory.dto.producao;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ProductionRequest(
        @NotNull(message = "O ID do produto é obrigatório")
        Long productId,

        @NotNull(message = "A quantidade a ser produzida é obrigatória")
        @Min(value = 1, message = "A quantidade produzida deve ser no mínimo 1")
        Integer quantity
) {}
