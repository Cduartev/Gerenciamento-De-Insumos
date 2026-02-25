package com.projeto.gerenciamento.de.insumos.dto.produto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.List;

public record CriarProdutoRequest(

        @NotBlank(message = "O codigo é obrigatorio.")
        @Size(max = 50, message = "O codigo deve ter no maximo 50 caracteres.")
        String codigo,

        @NotBlank(message = "O nome é obrigatorio.")
        @Size(max = 120, message = "O nome deve ter no maximo 120 caracteres.")
        String nome,

        @NotNull(message = "O valor é obrigatorio.")
        @DecimalMin(value = "0.01", inclusive = true, message = "O valor deve ser maior que zero.")
        BigDecimal valor,

        @NotEmpty(message = "A composicao do produto é obrigatoria e deve possuir ao menos um item.")
        List<@Valid ItemComposicaoProdutoRequest> itensComposicao
) {
}
